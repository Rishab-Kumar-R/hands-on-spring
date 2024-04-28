package com.rishab.service;

import com.rishab.model.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> findAllArtists();

    Artist findArtistById(Integer id);

    Artist save(Artist artist);

    void deleteArtist(Integer id);
}
