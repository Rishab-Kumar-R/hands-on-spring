package com.rishab.dao;

import com.rishab.entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();

    Product findById(Integer id);

    Product save(Product product);

    void deleteById(Integer id);
}
