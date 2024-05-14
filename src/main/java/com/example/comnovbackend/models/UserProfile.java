package com.example.comnovbackend.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


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

    private String email;
    private String username;

    @Getter @Setter
    private Date joinDate;
    public UserProfile(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.joinDate = user.getJoinDate();
    }

    public UserProfile() {

    }

}
