package com.rishab.service;

import com.rishab.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    Product save(Product product);

    void deleteById(Integer id);
}
