package com.rishab.controller;

import com.rishab.model.Artist;
import com.rishab.service.ArtistService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/list")
    public String listArtists(Model model) {
        List<Artist> artists = artistService.findAllArtists();
        model.addAttribute("artists", artists);
        return "artists/list-artists";
    }

    @GetMapping("/add-artist")
    public String addArtistForm(Model model) {
        Artist artist = new Artist();
        model.addAttribute("artist", artist);
        return "artists/add-artist";
    }

    @PostMapping("/add")
    public String addArtist(@ModelAttribute("artist") Artist artist) {
        artistService.save(artist);
        return "redirect:/artists/list";
    }

    @GetMapping("/edit/{id}")
    public String editArtistForm(@PathVariable Integer id, Model model) {
        Artist artist = artistService.findArtistById(id);
        model.addAttribute("artist", artist);
        return "artists/add-artist";
    }

    @GetMapping("/delete/{id}")
    public String deleteArtist(@PathVariable Integer id) {
        artistService.deleteArtist(id);
        return "redirect:/artists/list";
    }
}
