package com.rishab.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "label")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "label_detail")
    private String labelDetail;

    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
        name = "artist_label",
        joinColumns = @JoinColumn(name = "label_id"),
        inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> artists;

    public Label() {
    }

    public Label(String name, String labelDetail) {
        this.name = name;
        this.labelDetail = labelDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabelDetail() {
        return labelDetail;
    }

    public void setLabelDetail(String labelDetail) {
        this.labelDetail = labelDetail;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public void addArtists(Artist artist) {
        if (artists == null) {
            artists = new ArrayList<>();
        }
        artists.add(artist);
    }

    @Override
    public String toString() {
        return "Label{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", labelDetail='" + labelDetail + '\'' +
            '}';
    }
}
