package com.example.comnovbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "published_books")
@Getter @Setter
public class PublishedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @Getter @Setter
    private Book book;

    @Column(name = "date", nullable = false)
    @Getter @Setter
    private Date publishDate;

    @Column(name = "description",nullable = false )
    @Getter @Setter
    private String description;


}
