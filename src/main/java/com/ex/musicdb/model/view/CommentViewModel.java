package com.ex.musicdb.model.view;

import java.time.LocalDateTime;

public class CommentViewModel {

    private String textContent;
    private LocalDateTime addedOn;
    private String user;
    private String albumEntity;


    public CommentViewModel() {
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentViewModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public CommentViewModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public String getUser() {
        return user;
    }

    public CommentViewModel setUser(String user) {
        this.user = user;
        return this;
    }

    public String getAlbumEntity() {
        return albumEntity;
    }

    public CommentViewModel setAlbumEntity(String albumEntity) {
        this.albumEntity = albumEntity;
        return this;
    }
}
