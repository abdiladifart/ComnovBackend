package com.example.comnovbackend.controller;// AdminController.java

import com.example.comnovbackend.dto.UserDTO;
import com.example.comnovbackend.models.Book;
import com.example.comnovbackend.models.Genre;
import com.example.comnovbackend.models.Role;
import com.example.comnovbackend.models.User;
import com.example.comnovbackend.service.impl.AdminService;
import com.example.comnovbackend.service.BookService;
import com.example.comnovbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @Autowired
    private UserService userService;  // Assumes UserService is configured to interact with UserRepository

    @Autowired
    private BookService bookService;

    // Book CRUD endpoints
    @PostMapping("/books")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> publishBook(
            @RequestParam("title") String title,
            @RequestParam("genre") String genre,
            @RequestParam("description") String description,
            @RequestParam("author") String author,
            @RequestParam("publishDate") String publishDate,
            @RequestParam("coverFile") MultipartFile coverFile,
            @RequestParam("contentFile") MultipartFile contentFile,
            Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in.");
        }

        try {
            String email = principal.getName();
            Book book = bookService.publishBook(title, genre, description, author, publishDate, coverFile, contentFile, email);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while publishing the book: " + e.getMessage());
        }
    }


    @GetMapping("/books")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(adminService.updateBook(id, book));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        adminService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

//     User CRUD endpoints
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(adminService.updateUser(id, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    // Genre endpoints
    @PostMapping("/genres")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        return ResponseEntity.ok(adminService.createGenre(genre));
    }

    @GetMapping("/genres")
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(adminService.getAllGenres());
    }

    @PutMapping("/genres/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
        return ResponseEntity.ok(adminService.updateGenre(id, genre));
    }

    @DeleteMapping("/genres/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        adminService.deleteGenre(id);
        return ResponseEntity.ok().build();
    }

    // Role endpoints
    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return ResponseEntity.ok(adminService.createRole(role));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(adminService.getAllRoles());
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        return ResponseEntity.ok(adminService.updateRole(id, role));
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        adminService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
