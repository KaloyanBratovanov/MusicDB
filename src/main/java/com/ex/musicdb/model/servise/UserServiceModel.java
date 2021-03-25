package com.ex.musicdb.model.servise;

import javax.persistence.Column;

public class UserServiceModel {


    private Long id;
    private String username;
    private String password;
    private String fullname;

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFullname() {
        return fullname;
    }

    public UserServiceModel setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }
}
