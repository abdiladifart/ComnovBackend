package com.example.comnovbackend.repository;

import com.example.comnovbackend.models.Genre;
import com.example.comnovbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
