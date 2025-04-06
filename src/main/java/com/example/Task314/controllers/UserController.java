package com.example.Task314.controllers;

import com.example.Task314.models.User;
import com.example.Task314.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String run(){
        userService.getAllUsers();
        User user = new User(3L,"James","Brown",(byte)29);
        String first = userService.createUser(user);
        user.setName("Thomas");
        user.setLastName("Shelby");
        String second = userService.updateUser(user);
        String third = userService.deleteUser(user.getId());
        return first+second+third;
    }
}