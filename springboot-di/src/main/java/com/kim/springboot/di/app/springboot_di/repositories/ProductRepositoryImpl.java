package com.kim.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.kim.springboot.di.app.springboot_di.models.Product;

// Atraves de la anotacion @Repository se le puede dar un nombre altenativo a la clase de repositorio a la hora de ser injectado en el Service.
// @SessionScope
@Primary
@Repository("productList")
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> data;

    public ProductRepositoryImpl() {
        this.data = Arrays.asList(
            new Product(1L, "Memoria Elefante Rosa", 300L),
            new Product(2L, "CPU 6502", 900L),
            new Product(3L, "Teclado Gominolas yeso", 100L),
            new Product(4L, "Motherfuckerboard el Pirata Byte", 1000L)
        );
    }

    @Override
    public List<Product> findAll() {
        return data;
    }

    @Override
    public Product findById(Long id) {
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

}
