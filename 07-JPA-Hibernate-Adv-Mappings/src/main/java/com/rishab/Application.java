package com.rishab;

import com.rishab.dao.AppDAO;
import com.rishab.entity.Artist;
import com.rishab.entity.ArtistDetail;
import com.rishab.entity.Label;
import com.rishab.entity.Song;
import com.rishab.entity.SongReview;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            // createArtist(appDAO);
            // findArtist(appDAO);
            // deleteArtist(appDAO);
            // findArtistDetail(appDAO);
            // deleteArtistDetail(appDAO);
            // createArtistWithSongs(appDAO);
            // findArtistWithSongs(appDAO);
            // findSongsForArtist(appDAO);
            // findArtistWithSongsJoinFetch(appDAO);
            // updateArtist(appDAO);
            // updateSong(appDAO);
            // deleteSong(appDAO);
            // createSongsAndReviews(appDAO);
            // findSongAndReviewsBySongId(appDAO);
            // deleteSongAndReviews(appDAO);
            // createLabelWithArtists(appDAO);
            // findArtistAndLabelsByArtistId(appDAO);
            // findLabelAndArtistsByLabelId(appDAO);
            // addMoreLabelsToArtist(appDAO);

            deleteLabel(appDAO);
        };
    }

    private void deleteLabel(AppDAO appDAO) {
        long id = 3;
        System.out.println("Deleting label by id..." + id);
        appDAO.deleteLabelById(id);
        System.out.println("Deleted label by id..." + id);
    }

    private void addMoreLabelsToArtist(AppDAO appDAO) {
        long artistId = 4;

        System.out.println("Finding artist by id..." + artistId);
        Artist artist = appDAO.findArtistById(artistId);
        System.out.println("Found artist by id..." + artist);

        Label mercuryRecords = new Label("Mercury Records", "Mercury Records is an American record label owned by Universal Music Group.");
        Label interscopeRecords = new Label("Interscope Records", "Interscope Records is an American record label owned by Universal Music Group.");

        System.out.println("Saving labels..." + mercuryRecords + ", " + interscopeRecords);
        appDAO.save(mercuryRecords);
        appDAO.save(interscopeRecords);
        System.out.println("Saved labels..." + mercuryRecords + ", " + interscopeRecords);

        artist.addLabels(mercuryRecords);
        artist.addLabels(interscopeRecords);

        System.out.println("Updating artist with labels..." + artist);
        appDAO.updateArtist(artist);
        System.out.println("Updated artist with labels..." + artist);
        System.out.println("The labels are..." + artist.getLabels());
    }

    private void findLabelAndArtistsByLabelId(AppDAO appDAO) {
        long id = 1;
        System.out.println("Finding label with artists by id..." + id);
        Label label = appDAO.findLabelAndArtistByLabelId(id);
        System.out.println("Found label with artists by id..." + label);
        System.out.println("The artists are..." + label.getArtists());
    }

    private void findArtistAndLabelsByArtistId(AppDAO appDAO) {
        long id = 1;
        System.out.println("Finding artist with labels by id..." + id);
        Artist artist = appDAO.findArtistAndLabelsByArtistId(id);
        System.out.println("Found artist with labels by id..." + artist);
        System.out.println("The labels are..." + artist.getLabels());
    }

    private void createLabelWithArtists(AppDAO appDAO) {
        Label republicRecords = new Label("Republic Records", "Republic Records is an American record label owned by Universal Music Group.");

        Artist arianaGrande = new Artist("Ariana", "Grande", "ariana.notthesmall@email.com");
        ArtistDetail arianaGrandeDetail = new ArtistDetail("Ariana Grande is an American singer and actress.", "Pop, R&B");
        arianaGrande.setArtistDetail(arianaGrandeDetail);

        Song positions = new Song("Positions", "2:52");
        Song nasty = new Song("Nasty", "3:20");
        arianaGrande.addSongs(positions, nasty);

        Artist postMalone = new Artist("Post", "Malone", "postyghosty@email.com");
        ArtistDetail postMaloneDetail = new ArtistDetail("Post Malone is an American rapper, singer, and songwriter.", "Hip Hop, Pop");
        postMalone.setArtistDetail(postMaloneDetail);

        Song circles = new Song("Circles", "3:35");
        Song rockstar = new Song("rockstar", "3:38");
        Song sunflower = new Song("Sunflower", "2:38");
        postMalone.addSongs(circles, rockstar, sunflower);

        republicRecords.addArtists(arianaGrande);
        republicRecords.addArtists(postMalone);

        System.out.println("Saving label with artists..." + republicRecords);
        appDAO.save(republicRecords);
        System.out.println("Saved label with artists..." + republicRecords);
        System.out.println("The artists are..." + republicRecords.getArtists());
    }

    private void deleteSongAndReviews(AppDAO appDAO) {
        long id = 13;
        System.out.println("Deleting song and reviews by id..." + id);
        appDAO.deleteSongById(id);
        System.out.println("Deleted song and reviews by id..." + id);
    }

    private void findSongAndReviewsBySongId(AppDAO appDAO) {
        long id = 13;
        System.out.println("Finding song and reviews by id..." + id);
        Song song = appDAO.findSongAndReviewsBySongId(id);
        System.out.println("Found song and reviews by id..." + song);
        System.out.println("The reviews are..." + song.getSongReviews());
    }

    private void createSongsAndReviews(AppDAO appDAO) {
        Song song = new Song("Too Late", "4:00");
        song.addSongReview(new SongReview("How can you not love this song?"));
        song.addSongReview(new SongReview("This song is a banger!"));

        System.out.println("Saving song with reviews..." + song);
        appDAO.save(song);
        System.out.println("Saved song with reviews..." + song);
        System.out.println("The reviews are..." + song.getSongReviews());
    }

    private void deleteSong(AppDAO appDAO) {
        long id = 3;
        System.out.println("Deleting song by id..." + id);
        appDAO.deleteSongById(id);
        System.out.println("Deleted song by id..." + id);
    }

    private void updateSong(AppDAO appDAO) {
        long id = 1;
        System.out.println("Finding song by id..." + id);
        Song song = appDAO.findSongById(id);
        System.out.println("Found song by id..." + song);

        System.out.println("Updating song..." + song);
        song.setTitle("Don't Start Now (Remix)");
        song.setDuration("3:33");
        appDAO.updateSong(song);
        System.out.println("Updated song..." + song);
    }

    private void updateArtist(AppDAO appDAO) {
        long id = 4;
        System.out.println("Finding artist by id..." + id);
        Artist artist = appDAO.findArtistById(id);
        System.out.println("Found artist by id..." + artist);

        artist.setEmail("dualipavibes@gmail.com");
        artist.getArtistDetail().setGenre("Pop, Dance, Electropop");

        System.out.println("Updating artist..." + artist);
        appDAO.updateArtist(artist);
        System.out.println("Updated artist..." + artist);
    }

    private void findArtistWithSongsJoinFetch(AppDAO appDAO) {
        long id = 4;
        System.out.println("Finding artist with songs by id..." + id);
        Artist artist = appDAO.findArtistByIdJoinFetch(id);
        System.out.println("Found artist with songs by id..." + artist);
        System.out.println("The songs are..." + artist.getSongs());
    }

    private void findSongsForArtist(AppDAO appDAO) {
        long id = 4;
        System.out.println("Finding artist with songs by id..." + id);
        Artist artist = appDAO.findArtistById(id);
        System.out.println("Found artist with songs by id..." + artist);

        System.out.println("Finding songs for artist by id..." + id);
        List<Song> songs = appDAO.findSongsByArtistId(id);
        artist.setSongs(songs);
        System.out.println("Found songs for artist by id..." + artist.getSongs());
    }

    private void findArtistWithSongs(AppDAO appDAO) {
        long id = 4;
        System.out.println("Finding artist with songs by id..." + id);
        Artist artist = appDAO.findArtistById(id);
        System.out.println("Found artist with songs by id..." + artist);
        System.out.println("The songs are..." + artist.getSongs());
    }

    private void createArtistWithSongs(AppDAO appDAO) {
        Artist artist = new Artist("Dua", "Lipa", "dua.beats@gmail.com");
        ArtistDetail artistDetail = new ArtistDetail("Dua Lipa is an English singer and songwriter.", "Pop");

        artist.setArtistDetail(artistDetail);

        Song song1 = new Song("Don't Start Now", "3:03");
        Song song2 = new Song("Levitating", "3:23");
        Song song3 = new Song("Physical", "3:13");

        artist.addSongs(song1, song2, song3);
        System.out.println("Saving artist with songs..." + artist);
        System.out.println("The songs are..." + artist.getSongs());
        appDAO.save(artist);
        System.out.println("Saved artist with songs..." + artist);
    }

    private void deleteArtistDetail(AppDAO appDAO) {
        long id = 3;
        System.out.println("Deleting artist detail by id..." + id);
        appDAO.deleteArtistDetailById(id);
        System.out.println("Deleted artist detail by id..." + id);
    }

    private void findArtistDetail(AppDAO appDAO) {
        long id = 2;
        System.out.println("Finding artist detail by id..." + id);
        ArtistDetail artistDetail = appDAO.findArtistDetailById(id);
        System.out.println("Found artist detail by id..." + artistDetail);
        System.out.println("Artist..." + artistDetail.getArtist());
    }

    private void deleteArtist(AppDAO appDAO) {
        long id = 4;
        System.out.println("Deleting artist by id..." + id);
        appDAO.deleteArtistById(id);
        System.out.println("Deleted artist by id..." + id);
    }

    private void findArtist(AppDAO appDAO) {
        long id = 2;
        System.out.println("Finding artist by id..." + id);
        Artist artist = appDAO.findArtistById(id);
        System.out.println("Found artist by id..." + artist);
        System.out.println("Artist detail..." + artist.getArtistDetail());
    }

    private void createArtist(AppDAO appDAO) {
        Artist artist = new Artist("Billie", "Eilish", "badguybillie@musicmischief.com");
        ArtistDetail artistDetail = new ArtistDetail("Billie Eilish Pirate Baird O'Connell is an American singer and songwriter.", "Pop");

        artist.setArtistDetail(artistDetail);

        System.out.println("Saving artist..." + artist);
        appDAO.save(artist);
        System.out.println("Saved artist..." + artist);
    }
}
