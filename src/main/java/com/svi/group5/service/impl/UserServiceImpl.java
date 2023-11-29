package com.svi.group5.service.impl;

import com.svi.group5.dao.UserRepository;
import com.svi.group5.dto.UserDataDto;
import com.svi.group5.dto.UserRegistrationDto;
import com.svi.group5.entity.User;
import com.svi.group5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.svi.group5.mapper.UserMapper.convertToUser;
import static com.svi.group5.mapper.UserMapper.convertToUserDataDto;

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
    public User registerUser(User user) {
        String email = user.getEmail();
        boolean isEmailTaken = userRepository.existsByEmail(email);
        if (isEmailTaken) {
            throw new IllegalStateException("Email " + email + " is already taken");
        }

        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
}
