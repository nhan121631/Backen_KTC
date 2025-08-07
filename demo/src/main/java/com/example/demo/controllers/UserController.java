package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.Auth.UserResponseDto;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public UserResponseDto findByUsername(@PathVariable("username") String username) {
        UserResponseDto user = userService.getUserByUsername(username);
        return user;
    }

}
