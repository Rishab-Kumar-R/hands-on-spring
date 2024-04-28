package com.rishab.dao;

import com.rishab.entity.Product;

import jakarta.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private final EntityManager entityManager;

    public ProductDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("FROM Product", Product.class).getResultList();
    }

    @Override
    public Product findById(Integer id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product save(Product product) {
        return entityManager.merge(product); // if id is null, insert, else update
    }

    @Override
    public void deleteById(Integer id) {
        entityManager.remove(findById(id));
    }
}
