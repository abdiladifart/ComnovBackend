package com.example.comnovbackend.dto;

import com.example.comnovbackend.models.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;

public class BookDTO {
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String genre;

    @Getter @Setter
    private String cover; // This should be a base64 encoded string

    // Add other fields as necessary
    @Getter @Setter
    private String content;


    // Standard getters and setters

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.genre = book.getGenre();
        if (book.getCover() != null && book.getCover().length > 0) {
            this.cover = Base64.getEncoder().encodeToString(book.getCover());
        } else {
            this.cover = null; // Ensure this is set to null if no cover data is present
        } if (book.getContent() != null && book.getContent().length > 0) {
            this.content = Base64.getEncoder().encodeToString(book.getContent());
        } else {
            this.content = null; // Ensure this is set to null if no content data is present
        }
    }

    // Standard getters and setters
}