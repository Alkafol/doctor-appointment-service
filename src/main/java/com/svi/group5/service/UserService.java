package com.svi.group5.service;

import com.svi.group5.entity.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerUser(User user);
}
