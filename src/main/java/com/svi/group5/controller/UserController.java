package com.svi.group5.controller;

import com.svi.group5.dto.UserDataDto;
import com.svi.group5.dto.UserCreateDto;
import com.svi.group5.dto.UserLoginDto;
import com.svi.group5.entity.User;
import com.svi.group5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.svi.group5.mapper.UserMapper.convertToUser;
import static com.svi.group5.mapper.UserMapper.convertToUserDataDto;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public UserDataDto registerUser(@RequestBody UserCreateDto userCreateDto) {
        User user = convertToUser(userCreateDto);
        User savedUser = userService.registerUser(user);
        return convertToUserDataDto(savedUser);
    }

    @PostMapping("/login")
    public UserDataDto loginUser(@RequestBody UserLoginDto userLoginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userLoginDto.getEmail(), userLoginDto.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userService.findByEmail(userLoginDto.getEmail());
        return convertToUserDataDto(user);
    }
}
