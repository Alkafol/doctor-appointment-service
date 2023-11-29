package com.svi.group5.service;

import com.svi.group5.dto.UserDataDto;
import com.svi.group5.dto.UserRegistrationDto;

import org.springframework.stereotype.Service;

public interface UserService {
    UserDataDto registerUser(UserRegistrationDto userRegistrationDto);
}
