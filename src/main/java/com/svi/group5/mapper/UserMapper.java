package com.svi.group5.mapper;

import com.svi.group5.dto.UserDataDto;
import com.svi.group5.dto.UserRegistrationDto;
import com.svi.group5.entity.Client;
import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.User;
import com.svi.group5.enums.Role;

public class UserMapper {

    public static User convertToUser(UserRegistrationDto userRegistrationDto) {
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

    public static UserDataDto convertToUserDataDto(User user) {
        return new UserDataDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getEmail(),
                user.getDateOfBirth()
        );
    }
}
