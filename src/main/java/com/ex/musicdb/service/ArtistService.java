package com.ex.musicdb.service;

import com.ex.musicdb.model.entities.ArtistEntity;
import com.ex.musicdb.model.view.ArtistViewModel;

import java.util.List;

public interface ArtistService {


    void seedArtist();

    ArtistEntity findByName(String artist);

    List<String> findAllArtists();
}
