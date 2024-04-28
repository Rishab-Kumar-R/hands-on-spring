package com.rishab.controller;

import com.rishab.entity.Product;
import com.rishab.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Integer id) {
        Product product = productService.findById(id);

        if (product == null) {
            throw new RuntimeException("Product id not found - " + id);
        }

        return product;
    }

    @PostMapping("/products")
    public Product save(@RequestBody Product product) {
        product.setId(0);
        return productService.save(product);
    }

    @PutMapping("/products")
    public Product update(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/products/{id}")
    public String deleteById(@PathVariable Integer id) {
        Product product = productService.findById(id);

        if (product == null) {
            throw new RuntimeException("Product id not found - " + id);
        }

        productService.deleteById(id);
        return "Deleted product id - " + id;
    }
}
