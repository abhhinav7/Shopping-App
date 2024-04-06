package com.HyperSrot.assignment.shopping_app.service;

import com.HyperSrot.assignment.shopping_app.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Optional<Product> findById(Long id);

    void deleteById(Long id);
}
