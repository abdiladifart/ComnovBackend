package com.example.comnovbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
@Getter @Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private String description;

    @Lob
    private byte[] cover;

    @Lob
    private byte[] content;

    private String author;
    private String publisher;

    @Column(name = "publish_date", nullable = false)
    private Date publishDate;

    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Book() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", cover=" + Arrays.toString(cover) +
                ", content=" + Arrays.toString(content) +
                '}';
    }

    public Book(String title, String genre, String description, byte[] cover, byte[] content, User user) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.cover = cover;
        this.content = content;
        this.user = user;
    }
}
