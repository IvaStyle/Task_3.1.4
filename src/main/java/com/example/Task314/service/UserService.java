package com.example.Task314.service;

import com.example.Task314.models.User;

import java.util.List;

public interface UserService {

    void getAllUsers();
    String createUser(User user);
    String updateUser(User user);
    String deleteUser(Long id);
}