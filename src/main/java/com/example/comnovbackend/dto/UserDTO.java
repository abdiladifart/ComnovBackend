package com.example.comnovbackend.dto;
import lombok.Getter;
import lombok.Setter;
public class UserDTO {
    @Getter @Setter
    private String username;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    // Default constructor
    public UserDTO() {
    }

    // Constructor
    public UserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}