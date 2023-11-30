package com.svi.group5.controller;

import com.svi.group5.dto.UserDataDto;
import com.svi.group5.dto.UserCreateDto;
import com.svi.group5.entity.User;
import com.svi.group5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.svi.group5.mapper.UserMapper.convertToUser;
import static com.svi.group5.mapper.UserMapper.convertToUserDataDto;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDataDto registerUser(@RequestBody UserCreateDto userCreateDto){
        User user = convertToUser(userCreateDto);
        User savedUser = userService.registerUser(user);
        return convertToUserDataDto(savedUser);
    }
}
