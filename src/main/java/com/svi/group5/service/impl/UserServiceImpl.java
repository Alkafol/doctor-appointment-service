package com.svi.group5.service.impl;

import com.svi.group5.dao.UserRepository;
import com.svi.group5.dto.UserDataDto;
import com.svi.group5.dto.UserRegistrationDto;
import com.svi.group5.entity.Client;
import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.User;
import com.svi.group5.enums.Role;
import com.svi.group5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    @Override
    public UserDataDto registerUser(UserRegistrationDto userRegistrationDto) {
        String email = userRegistrationDto.getEmail();
        boolean isEmailTaken = userRepository.existsByEmail(email);
        if (isEmailTaken) {
            throw new IllegalStateException("Email " + email + " is already taken");
        }

        String password = userRegistrationDto.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        userRegistrationDto.setPassword(encodedPassword);
        User user = convertToUser(userRegistrationDto);

        User savedUser = userRepository.save(user);
        return convertToUserDataDto(savedUser);
    }

    private UserDataDto convertToUserDataDto(User user){
        return new UserDataDto(
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getEmail(),
                user.getDateOfBirth()
        );
    }


    private User convertToUser(UserRegistrationDto userRegistrationDto) {
        Role role = userRegistrationDto.getRole();
        User user;

        user = switch (role) {
            case CLIENT -> new Client();
            case DOCTOR -> new Doctor();
        };

        user.setRole(role);
        user.setEmail(userRegistrationDto.getEmail());
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setMiddleName(userRegistrationDto.getMiddleName());
        user.setDateOfBirth(userRegistrationDto.getDateOfBirth());
        user.setPassword(userRegistrationDto.getPassword());

        return user;
    }
}
