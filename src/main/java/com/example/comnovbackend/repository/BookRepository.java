package com.example.comnovbackend.repository;
import com.example.comnovbackend.models.Book;
import com.example.comnovbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByUser(User user);
    List<Book> findByUserId(Long userId);

    List<Book> findAll();

}
