package com.example.comnovbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_profiles")
@Getter @Setter
public class UserProfile {

    @Id
    @Getter @Setter
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @Getter @Setter
    private User user;

    @Column(name = "profile_image")
    @Getter @Setter
    private String profileImage;

    @Column(name = "bio")
    @Getter @Setter
    private String bio;


}
