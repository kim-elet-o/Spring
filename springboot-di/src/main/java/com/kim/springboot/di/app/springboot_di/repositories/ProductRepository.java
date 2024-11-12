package com.kim.springboot.di.app.springboot_di.repositories;

import java.util.List;

import com.kim.springboot.di.app.springboot_di.models.Product;

public interface ProductRepository {
    List<Product> findAll();
    public Product findById(Long id);
}
