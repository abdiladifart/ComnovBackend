package com.example.comnovbackend.service;


import com.example.comnovbackend.dto.UserDTO;
import com.example.comnovbackend.models.User;

public interface UserService {
    User registerNewUserAccount(UserDTO userDTO);
}
