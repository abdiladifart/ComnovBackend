package com.example.comnovbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(nullable = false, unique = true)
    @Getter @Setter
    private String username;

    @Column(nullable = false, unique = true)
    @Getter @Setter
    private String email;

    @Column(nullable = false)
    @Getter @Setter
    private String password;

    @OneToMany(mappedBy = "user")
    @Getter @Setter
    private Set<PublishedBook> publishedBooks;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @Getter @Setter
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @Getter @Setter
    private Role role;



}
