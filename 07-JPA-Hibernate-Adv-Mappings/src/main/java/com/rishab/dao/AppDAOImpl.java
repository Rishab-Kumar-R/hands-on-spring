package com.rishab.dao;

import com.rishab.entity.Artist;
import com.rishab.entity.ArtistDetail;
import com.rishab.entity.Label;
import com.rishab.entity.Song;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    private final EntityManager entityManager;

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Artist artist) {
        entityManager.persist(artist);
    }

    @Override
    public Artist findArtistById(Long id) {
        return entityManager.find(Artist.class, id);
    }

    @Override
    @Transactional
    public void deleteArtistById(Long id) {
        Artist artist = findArtistById(id);
        List<Song> songs = artist.getSongs();
        for (Song song : songs) {
            song.setArtist(null);
        }
        entityManager.remove(artist);
    }

    @Override
    public ArtistDetail findArtistDetailById(Long id) {
        return entityManager.find(ArtistDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteArtistDetailById(Long id) {
        ArtistDetail artistDetail = findArtistDetailById(id);

        // breaking the bidirectional link
        artistDetail.getArtist().setArtistDetail(null);
        entityManager.remove(artistDetail);
    }

    @Override
    public List<Song> findSongsByArtistId(Long id) {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s WHERE s.artist.id = :id", Song.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Artist findArtistByIdJoinFetch(Long id) {
        TypedQuery<Artist> query = entityManager.createQuery("SELECT a FROM Artist a JOIN FETCH a.songs JOIN FETCH a.artistDetail WHERE a.id = :id", Artist.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateArtist(Artist artist) {
        entityManager.merge(artist);
    }

    @Override
    public Song findSongById(Long id) {
        return entityManager.find(Song.class, id);
    }

    @Override
    @Transactional
    public void updateSong(Song song) {
        entityManager.merge(song);
    }

    @Override
    @Transactional
    public void deleteSongById(Long id) {
        Song song = findSongById(id);
        entityManager.remove(song);
    }

    @Override
    @Transactional
    public void save(Song song) {
        entityManager.persist(song);
    }

    @Override
    public Song findSongAndReviewsBySongId(Long id) {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s JOIN FETCH s.songReviews WHERE s.id = :id", Song.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void save(Label label) {
        entityManager.persist(label);
    }

    @Override
    public Artist findArtistAndLabelsByArtistId(Long id) {
        TypedQuery<Artist> query = entityManager.createQuery("SELECT a FROM Artist a JOIN FETCH a.labels WHERE a.id = :id", Artist.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Label findLabelAndArtistByLabelId(Long id) {
        TypedQuery<Label> query = entityManager.createQuery("SELECT l FROM Label l JOIN FETCH l.artists WHERE l.id = :id", Label.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteLabelById(Long id) {
        Label label = entityManager.find(Label.class, id);
        entityManager.remove(label);
    }

}
