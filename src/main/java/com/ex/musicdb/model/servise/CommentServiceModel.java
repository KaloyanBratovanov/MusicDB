package com.ex.musicdb.model.servise;

import com.ex.musicdb.model.entities.AlbumEntity;
import com.ex.musicdb.model.entities.UserEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class CommentServiceModel {


    private String textContent;
    private LocalDateTime addedOn;
    private String user;
    private String albumEntity;

    public CommentServiceModel() {
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentServiceModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public CommentServiceModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public String getUser() {
        return user;
    }

    public CommentServiceModel setUser(String user) {
        this.user = user;
        return this;
    }

    public String getAlbumEntity() {
        return albumEntity;
    }

    public CommentServiceModel setAlbumEntity(String albumEntity) {
        this.albumEntity = albumEntity;
        return this;
    }
}
