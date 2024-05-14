package com.example.comnovbackend.models;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "published_books")
@Getter @Setter
public class PublishedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "date", nullable = false)
    private Date publishDate;

    @Column(name = "description", nullable = false)
    private String description;

}
