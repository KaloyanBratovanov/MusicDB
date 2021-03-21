package com.ex.musicdb.service;

import com.ex.musicdb.model.view.ArtistViewModel;

import java.util.List;

public interface ArtistService {

    List<String> findAllArtist();

    void seedArtist();
}
