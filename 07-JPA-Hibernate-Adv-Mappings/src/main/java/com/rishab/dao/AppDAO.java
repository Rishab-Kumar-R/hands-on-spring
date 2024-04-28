package com.rishab.dao;

import com.rishab.entity.Artist;
import com.rishab.entity.ArtistDetail;
import com.rishab.entity.Label;
import com.rishab.entity.Song;

import java.util.List;

public interface AppDAO {
    void save(Artist artist);

    Artist findArtistById(Long id);

    void deleteArtistById(Long id);

    ArtistDetail findArtistDetailById(Long id);

    void deleteArtistDetailById(Long id);

    List<Song> findSongsByArtistId(Long id);

    Artist findArtistByIdJoinFetch(Long id);

    void updateArtist(Artist artist);

    Song findSongById(Long id);

    void updateSong(Song song);

    void deleteSongById(Long id);

    void save(Song song);

    Song findSongAndReviewsBySongId(Long id);

    void save(Label label);

    Artist findArtistAndLabelsByArtistId(Long id);

    Label findLabelAndArtistByLabelId(Long id);

    void deleteLabelById(Long id);
}
