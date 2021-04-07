package com.ex.musicdb.model.entities;

import com.ex.musicdb.model.entities.enums.Genre;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class ArticleEntity extends BaseEntity{

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(nullable = false)
    private String content;
    @ManyToOne
    private UserEntity userEntity;

    public ArticleEntity() {
    }

    public String getTitle() {
        return title;
    }

    public ArticleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public ArticleEntity setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public ArticleEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
