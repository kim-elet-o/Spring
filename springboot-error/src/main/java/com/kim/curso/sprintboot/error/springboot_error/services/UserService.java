package com.kim.curso.sprintboot.error.springboot_error.services;

import java.util.List;
import java.util.Optional;

import com.kim.curso.sprintboot.error.springboot_error.models.domain.User;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(Long id);
    
}
