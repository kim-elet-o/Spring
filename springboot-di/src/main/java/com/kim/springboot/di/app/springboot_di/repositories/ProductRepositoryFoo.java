package com.kim.springboot.di.app.springboot_di.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kim.springboot.di.app.springboot_di.models.Product;

@Repository
public class ProductRepositoryFoo implements ProductRepository {

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(100L, "Jato Jumano", 22L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(id, "Jato Jumano", 22L);
    }

}
