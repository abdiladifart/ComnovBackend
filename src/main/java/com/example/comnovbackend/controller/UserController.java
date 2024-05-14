package com.example.comnovbackend.controller;

import ch.qos.logback.classic.Logger;
import com.example.comnovbackend.config.JWTGenerator;
import com.example.comnovbackend.dto.UserDTO;
import com.example.comnovbackend.models.User;
import com.example.comnovbackend.models.UserProfile;
import com.example.comnovbackend.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTGenerator jwtGenerator;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);



    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        User user = userService.registerUser(userDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String token = jwtGenerator.generateToken(authentication);

            // Create the response body with the token and user ID
            Map<String, Object> response = new HashMap<>();
            response.put("accessToken", token);
            response.put("userId", userDTO.getEmail());  // assuming email as user ID for simplicity

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Login failed for user {}: {}", userDTO.getEmail(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


//    @GetMapping("/profile")
//    public ResponseEntity<?> getUserProfile() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication.getName().equals("anonymousUser")) {
//            // Return an error response or prompt for authentication
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication is required");
//        }
//        String email = authentication.getName(); // The email should be the username set by Spring Security
//        System.out.println("Email from Authentication: " + email);
//        UserDTO userDTO = userService.getUserByEmail(email);
//        return ResponseEntity.ok(userDTO);
//    }

//    @GetMapping("/profile")
//    public ResponseEntity<?> getUserProfile(Authentication authentication) {
//        if (authentication == null || "anonymousUser".equals(authentication.getName())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication is required");
//        }
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String email = userDetails.getUsername(); // Get the username (email)
//
//        try {
//            UserDTO user = userService.getUserByEmail(email); // Assuming this method exists and fetches the user
//            return ResponseEntity.ok(user);
//        } catch (Exception e) {
//            logger.error("Profile fetch failed for user {}: {}", email, e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to fetch profile");
//        }
//    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Authentication authentication) {
        if (authentication == null || "anonymousUser".equals(authentication.getName())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication is required");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        try {
            UserDTO user = userService.getUserByEmail(email);
            System.out.println("Sending user data with books: " + user.getBooks());  // Log to check
            return ResponseEntity.ok(user);  // Assuming conversion here
        } catch (Exception e) {
            logger.error("Profile fetch failed for user {}: {}", email, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to fetch profile");
        }
    }

}
