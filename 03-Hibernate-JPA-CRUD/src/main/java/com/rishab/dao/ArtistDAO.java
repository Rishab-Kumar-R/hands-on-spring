package com.rishab.dao;

import com.rishab.entity.Artist;

import java.util.List;

public interface ArtistDAO {
    void save(Artist artist);

    Artist findById(Integer id);

    List<Artist> findAll();

    List<Artist> findByLastName(String lastName);

    void update(Artist artist);

    void delete(Integer id);

    int deleteAll();
}
