package com.example.comnovbackend.dto;
import com.example.comnovbackend.models.Role;
import com.example.comnovbackend.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO  {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private Date joinDate;

    @Getter @Setter
    private List<BookDTO> books;

    @Getter @Setter
    private Role role;


    // Default constructor
    public UserDTO() {
    }

    // Constructor
    public UserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.books = user.getBooks().stream().map(BookDTO::new).collect(Collectors.toList());
    }

    public UserDTO(Long id, String email, String username) {
        this.username = username;
        this.email = email;
        this.id = id;
    }
}