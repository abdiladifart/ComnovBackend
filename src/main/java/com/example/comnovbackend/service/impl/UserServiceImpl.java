package com.example.comnovbackend.service.impl;

import com.example.comnovbackend.dto.UserDTO;
import com.example.comnovbackend.models.User;
import com.example.comnovbackend.models.UserProfile;
import com.example.comnovbackend.repository.UserRepository;
import com.example.comnovbackend.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setJoinDate(new Date());  // Set the join date to current date/time
        return userRepository.save(user);
    }

    @Override
    public UserDTO loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }


        return new UserDTO(user.getId(), user.getEmail(), user.getUsername()); // Avoid returning the password
    }

    @Override
    public UserProfile getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.getUserProfile();
    }

//    public UserDTO getUserByEmail(String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        System.out.println("users books "+user.getBooks().size());
//        return new UserDTO(user.getId(), user.getEmail(), user.getUsername());
//    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDTO(user);
    }


    @Override
    public UserProfile getUserProfileByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new UserProfile(user);
    }

    public User getUserDetailsWithBooks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return user;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}