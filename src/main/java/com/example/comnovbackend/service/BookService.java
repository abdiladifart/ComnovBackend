package com.example.comnovbackend.service;

import com.example.comnovbackend.models.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    List<Book> getBooksByUserEmail(String email);

    List<Book> getBooksByUserId(Long id);

    Book publishBook(String title, String genre, String description, MultipartFile cover, MultipartFile content, String email) throws IOException;

    Book publishBook(String title, String genre, String description, String author, String publishDate, MultipartFile coverFile, MultipartFile contentFile, String email) throws IOException;
}
