package com.rishab.dao;

import com.rishab.model.Artist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistDAO extends JpaRepository<Artist, Integer> {
    List<Artist> findAllByOrderByLastNameAsc();
}
