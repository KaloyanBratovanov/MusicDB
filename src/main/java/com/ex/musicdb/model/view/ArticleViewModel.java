package com.ex.musicdb.model.view;

import com.ex.musicdb.model.entities.enums.Genre;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


public class ArticleViewModel {

    private Long id;
    private String title;
    private String imageUrl;
    private Genre genre;
    private String content;
    private String author;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdOn;

    public ArticleViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ArticleViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ArticleViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public ArticleViewModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleViewModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public ArticleViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public ArticleViewModel setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }
}
