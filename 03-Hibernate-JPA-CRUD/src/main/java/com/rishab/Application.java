package com.rishab;

import com.rishab.dao.ArtistDAO;
import com.rishab.entity.Artist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ArtistDAO artistDAO) {
        return runner -> {
            // createArtist(artistDAO);
            // createMultipleArtists(artistDAO);
            // readArtist(artistDAO);
            queryArtists(artistDAO);
            // queryArtistByLastName(artistDAO);
            // updateArtist(artistDAO);
            // deleteArtist(artistDAO);
            // deleteAllArtists(artistDAO);
        };
    }

    private void deleteAllArtists(ArtistDAO artistDAO) {
        System.out.println("Deleting all artists...");
        int deletedArtists = artistDAO.deleteAll();
        System.out.println("Artists deleted: " + deletedArtists);
    }

    private void deleteArtist(ArtistDAO artistDAO) {
        int artistId = 2;

        System.out.println("Deleting artist with id " + artistId + "...");
        artistDAO.delete(artistId);
        System.out.println("Artist deleted successfully.");
    }

    private void updateArtist(ArtistDAO artistDAO) {
        int artistId = 1;

        System.out.println("Updating artist with id " + artistId + "...");
        Artist artist = artistDAO.findById(artistId);
        artist.setFirstName("The");
        artist.setLastName("Weeknd");

        artistDAO.update(artist);
        System.out.println("Artist updated successfully.");
    }

    private void queryArtistByLastName(ArtistDAO artistDAO) {
        System.out.println("Querying artists by last name...");
        artistDAO.findByLastName("West").forEach(System.out::println);
    }

    private void queryArtists(ArtistDAO artistDAO) {
        System.out.println("Querying artists...");
        artistDAO.findAll().forEach(System.out::println);
    }

    private void readArtist(ArtistDAO artistDAO) {
        System.out.println("Creating artist...");
        Artist artist = new Artist("Eminem", "Mathers", "slimshady@marshallmail.com");

        System.out.println("Saving artist...");
        artistDAO.save(artist);
        System.out.println("Artist with id " + artist.getId() + " saved successfully.");

        System.out.println("Reading artist...");
        Artist foundArtist = artistDAO.findById(artist.getId());
        System.out.println("Artist found: " + foundArtist);
    }

    private void createMultipleArtists(ArtistDAO artistDAO) {
        System.out.println("Creating artists...");
        Artist kanye = new Artist("Kanye", "West", "yeezyking@yeetmail.com");
        Artist dua = new Artist("Dua", "Lipa", " levitatingqueen@dualipamail.com");
        Artist charlie = new Artist("Charlie", "Puth", "voicenotesmaster@charliemail.com");

        System.out.println("Saving artists...");
        artistDAO.save(kanye);
        artistDAO.save(dua);
        artistDAO.save(charlie);
        System.out.println("Artists saved successfully.");
    }

    private void createArtist(ArtistDAO artistDAO) {
        System.out.println("Creating artist...");
        Artist artist = new Artist("Abel", "Tesfaye", "blindinglights@weekendmail.com");

        System.out.println("Saving artist...");
        artistDAO.save(artist);
        System.out.println("Artist with id " + artist.getId() + " saved successfully.");
    }
}
