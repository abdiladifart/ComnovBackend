package com.example.comnovbackend.dto;

import lombok.Getter;
import lombok.Setter;

public class RoleDTO {

    @Getter @Setter
    private Long id;
    @Getter
    @Setter
    private String name;

    public RoleDTO() {}

    public RoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
