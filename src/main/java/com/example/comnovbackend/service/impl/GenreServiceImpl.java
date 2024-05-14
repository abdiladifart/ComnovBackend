package com.example.comnovbackend.service.impl;


import com.example.comnovbackend.dto.GenreDTO;
import com.example.comnovbackend.models.Genre;
import com.example.comnovbackend.repository.GenreRepository;
import com.example.comnovbackend.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(genre -> new GenreDTO(genre.getId(), genre.getName()))
                .collect(Collectors.toList());
    }
}
