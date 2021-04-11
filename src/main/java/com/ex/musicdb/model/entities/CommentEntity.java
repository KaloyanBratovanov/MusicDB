package com.ex.musicdb.model.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{


    @Column(nullable = false)
    private String textContent;
    @Column( nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime addedOn;
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private AlbumEntity albumEntity;

    public CommentEntity() {
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentEntity setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public CommentEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public AlbumEntity getAlbumEntity() {
        return albumEntity;
    }

    public CommentEntity setAlbumEntity(AlbumEntity albumEntity) {
        this.albumEntity = albumEntity;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public CommentEntity setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
