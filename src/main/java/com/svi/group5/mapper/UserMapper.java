package com.svi.group5.mapper;

import com.svi.group5.dto.UserDataDto;
import com.svi.group5.entity.User;

public class UserMapper {
    public static UserDataDto convert(User user) {
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
