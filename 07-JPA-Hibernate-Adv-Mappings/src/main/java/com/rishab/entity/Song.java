package com.rishab.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private String duration;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "song_id")
    private List<SongReview> songReviews;

    public Song() {
    }

    public Song(String title, String duration) {
        this.title = title;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<SongReview> getSongReviews() {
        return songReviews;
    }

    public void setSongReviews(List<SongReview> songReviews) {
        this.songReviews = songReviews;
    }

    public void addSongReview(SongReview songReview) {
        if (songReviews == null) {
            songReviews = new ArrayList<>();
        }
        songReviews.add(songReview);
    }

    @Override
    public String toString() {
        return "Song{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", duration='" + duration + '\'' +
            '}';
    }
}
