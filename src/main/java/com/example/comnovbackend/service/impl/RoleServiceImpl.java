package com.example.comnovbackend.service.impl;


import com.example.comnovbackend.dto.RoleDTO;
import com.example.comnovbackend.models.Role;
import com.example.comnovbackend.repository.RoleRepository;
import com.example.comnovbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> new RoleDTO(role.getId(), role.getName()))
                .collect(Collectors.toList());
    }
}