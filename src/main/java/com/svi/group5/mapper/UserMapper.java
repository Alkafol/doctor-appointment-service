package com.svi.group5.mapper;

import com.svi.group5.dto.UserDataDto;
import com.svi.group5.dto.UserCreateDto;
import com.svi.group5.entity.Client;
import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.User;
import com.svi.group5.enums.Role;

public class UserMapper {

    public static User convertToUser(UserCreateDto userCreateDto) {
        Role role = userCreateDto.getRole();
        User user;

        user = switch (role) {
            case CLIENT -> new Client();
            case DOCTOR -> new Doctor();
        };

        user.setRole(role);
        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setMiddleName(userCreateDto.getMiddleName());
        user.setDateOfBirth(userCreateDto.getDateOfBirth());
        user.setPassword(userCreateDto.getPassword());

        return user;
    }

    public static UserDataDto convertToUserDataDto(User user) {
        if (user == null) return null;
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
