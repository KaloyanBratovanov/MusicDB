package com.ex.musicdb.model.view;

import com.ex.musicdb.model.entities.enums.Genre;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public class AlbumCardViewModel {

    private Long id;
    private String name;
    private String imageUrl;
    private Integer copies;
    private BigDecimal price;
    private LocalDate releaseDate;
    private Genre genre;
    private String artist;

    public AlbumCardViewModel() {
    }

    public Long getId() {
        return id;
    }

    public AlbumCardViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumCardViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumCardViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumCardViewModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumCardViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AlbumCardViewModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public AlbumCardViewModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public AlbumCardViewModel setArtist(String artist) {
        this.artist = artist;
        return this;
    }
}
