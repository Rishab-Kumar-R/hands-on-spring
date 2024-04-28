package com.rishab.controller;

import com.rishab.entity.Artist;
import com.rishab.exception.ArtistNotFoundException;

import jakarta.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistRestController {
    private List<Artist> artists;

    @PostConstruct
    public void loadData() {
        artists = new ArrayList<>();
        artists.add(new Artist("The Weeknd", "Canadian singer, songwriter, and record producer"));
        artists.add(new Artist("Ariana Grande", "American singer and actress"));
        artists.add(new Artist("Billie Eilish", "American singer and songwriter"));
        artists.add(new Artist("Harry Styles", "English singer, songwriter, and actor"));
    }

    @GetMapping("/artists")
    public List<Artist> getArtists() {
        return artists;
    }

    @GetMapping("/artists/{artistId}")
    public Artist getArtistById(@PathVariable Integer artistId) {
        if (artistId < 0 || artistId >= artists.size()) {
            throw new ArtistNotFoundException("Artist id not found - " + artistId);
        }
        return artists.get(artistId);
    }
}
