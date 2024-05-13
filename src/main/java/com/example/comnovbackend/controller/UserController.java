package com.example.comnovbackend.controller;

import com.example.comnovbackend.dto.UserDTO;
import com.example.comnovbackend.models.User;
import com.example.comnovbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        User registered = userService.registerNewUserAccount(userDTO);
        return ResponseEntity.ok(registered);
    }
}
