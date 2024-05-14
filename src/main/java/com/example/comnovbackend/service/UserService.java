package com.example.comnovbackend.service;


import com.example.comnovbackend.dto.UserDTO;
import com.example.comnovbackend.models.User;
import com.example.comnovbackend.models.UserProfile;

import java.util.List;

public interface UserService {
    User registerUser(UserDTO userDTO);
    UserDTO loginUser(String username, String password);
    UserProfile getUserProfile(Long userId);

    UserDTO getUserByEmail(String email);

    UserProfile getUserProfileByEmail(String email);

    User getUserDetailsWithBooks(Long id);

    List<User> getAllUsers();
}
