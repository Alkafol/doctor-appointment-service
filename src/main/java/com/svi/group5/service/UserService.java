package com.svi.group5.service;

import com.svi.group5.dto.UserDataDto;
import com.svi.group5.dto.UserRegistrationDto;

import com.svi.group5.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User registerUser(User user);
}
