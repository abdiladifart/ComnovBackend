package com.example.comnovbackend.dto;

import lombok.Getter;
import lombok.Setter;

public class GenreDTO {

    @Getter
    @Setter
    private Long id;
    @Getter @Setter
    private String name;

    public GenreDTO() {}

    public GenreDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
