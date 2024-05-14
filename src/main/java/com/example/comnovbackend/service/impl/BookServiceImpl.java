package com.example.comnovbackend.service.impl;

import com.example.comnovbackend.models.Book;
import com.example.comnovbackend.models.User;

import com.example.comnovbackend.repository.BookRepository;
import com.example.comnovbackend.repository.UserRepository;
import com.example.comnovbackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    private final String uploadDir = "uploads/";

    public Book publishBook(String title, String genre, String description, MultipartFile cover, MultipartFile content, String email) throws IOException {
        // Save cover image
        String coverFileName = saveUploadedFile(cover);

        // Find the user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Book book = new Book();
        book.setTitle(title);
        book.setGenre(genre);
        book.setDescription(description);
        book.setCover(cover.getBytes());
        book.setContent(content.getBytes());
        book.setImageUrl(uploadDir + coverFileName); // Set the image URL
        book.setPublishDate(new Date()); // Set the current date as the publish date
        book.setAuthor(user.getUsername());
        book.setPublisher("Default Publisher"); // Set default publisher or retrieve from another source
        book.setUser(user); // Set the user

        // Add the book to the user's books collection
        user.getBooks().add(book);

        // Save the user and the book
        userRepository.save(user);
        return book;
    }

    @Override
    public Book publishBook(String title, String genre, String description, String author, String publishDate, MultipartFile cover, MultipartFile content, String email) throws IOException {
        String coverFileName = saveUploadedFile(cover);

        // Find the user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Book book = new Book();
        book.setTitle(title);
        book.setGenre(genre);
        book.setDescription(description);
        book.setCover(cover.getBytes());
        book.setContent(content.getBytes());
        book.setImageUrl(uploadDir + coverFileName); // Set the image URL
        book.setPublishDate(new Date()); // Set the current date as the publish date
        book.setAuthor(user.getUsername());
        book.setPublisher("Default Publisher"); // Set default publisher or retrieve from another source
        book.setUser(user); // Set the user

        // Add the book to the user's books collection
        user.getBooks().add(book);

        // Save the user and the book
        userRepository.save(user);
        return book;
    }


    public List<Book> getBooksByUserEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return bookRepository.findByUser(user);
    }

    public List<Book> getBooksByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return (List<Book>) user.getBooks();
    }

    private String saveUploadedFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);

        // Create directory if it doesn't exist
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // Save the file
        Files.write(filePath, file.getBytes());

        return fileName;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
