package com.rishab.service;

import com.rishab.dao.ArtistDAO;
import com.rishab.model.Artist;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistDAO artistDAO;

    public ArtistServiceImpl(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    @Override
    public List<Artist> findAllArtists() {
        return artistDAO.findAllByOrderByLastNameAsc();
    }

    @Override
    public Artist findArtistById(Integer id) {
        return artistDAO.findById(id).orElseThrow(() -> new RuntimeException("Artist with id " + id + " not found"));
    }

    @Override
    public Artist save(Artist artist) {
        return artistDAO.save(artist);
    }

    @Override
    public void deleteArtist(Integer id) {
        artistDAO.deleteById(id);
    }
}
