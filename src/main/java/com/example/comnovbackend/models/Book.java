package com.example.comnovbackend.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "books")
@Getter @Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(nullable = false)
    @Getter @Setter
    private String title;

    @Column(length = 5000)
    @Getter @Setter
    private String description;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    @Getter @Setter
    private Genre genre;

    @Getter @Setter
    private String author;

    @Getter @Setter
    private String publisher;

    @Column(name = "publish_date",nullable = false)
    @Getter @Setter
    private Date publishDate;

    @Column(name = "imageUrl",nullable = false)
    @Getter @Setter
    private String imageUrl;


    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    @Getter @Setter
    private User uploadedBy;


}
