package com.rishab.dao;

import com.rishab.entity.Artist;

import jakarta.persistence.EntityManager;

import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ArtistDAOImpl implements ArtistDAO {
    private final EntityManager entityManager;

    public ArtistDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Artist artist) {
        this.entityManager.persist(artist);
    }

    @Override
    public Artist findById(Integer id) {
        return this.entityManager.find(Artist.class, id);
    }

    @Override
    public List<Artist> findAll() {
        TypedQuery<Artist> query = this.entityManager.createQuery("FROM Artist", Artist.class);
        return query.getResultList();
    }

    @Override
    public List<Artist> findByLastName(String lastName) {
        TypedQuery<Artist> query = this.entityManager.createQuery("FROM Artist WHERE lastName = :lastName", Artist.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Artist artist) {
        this.entityManager.merge(artist);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Artist artist = this.entityManager.find(Artist.class, id);
        this.entityManager.remove(artist);
    }

    @Override
    @Transactional
    public int deleteAll() {
        return this.entityManager.createQuery("DELETE FROM Artist").executeUpdate();
    }
}
