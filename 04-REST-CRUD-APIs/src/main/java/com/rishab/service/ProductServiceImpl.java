package com.rishab.service;

import com.rishab.dao.ProductDAO;
import com.rishab.entity.Product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productDAO.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productDAO.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        productDAO.deleteById(id);
    }
}
