package com.example.comnovbackend.controller;

import com.example.comnovbackend.dto.BookDTO;
import com.example.comnovbackend.models.Book;
import com.example.comnovbackend.repository.BookRepository;
import com.example.comnovbackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/publish")
    public ResponseEntity<?> publishBook(@RequestParam("title") String title,
                                         @RequestParam("genre") String genre,
                                         @RequestParam("description") String description,
                                         @RequestParam("cover") MultipartFile cover,
                                         @RequestParam("content") MultipartFile content,
                                         Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in.");
        }
        try {
            String email = principal.getName(); // Assuming the principal's name is the user's email
            System.out.println("Publishing book for user: " + email);
            Book book = bookService.publishBook(title, genre, description, cover, content, email);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            System.out.println("Error while publishing book: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error while publishing the book: " + e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getBooksByUserEmail(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in.");
        }
        try {
            String email = principal.getName(); // Assuming the principal's name is the user's email
            List<Book> books = bookService.getBooksByUserEmail(email);
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            System.out.println("Error while fetching books for user: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error while fetching books for user: " + e.getMessage());
        }
    }



    @GetMapping("/user/{id}")
    public ResponseEntity<?> getBooksByUserId(@PathVariable Long id) {
        try {
            List<Book> books = bookService.getBooksByUserId(id);
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            System.out.println("Error while fetching books for user: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error while fetching books for user: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return ResponseEntity.ok(new BookDTO(book));
    }


}
