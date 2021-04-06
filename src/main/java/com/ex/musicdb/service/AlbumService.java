package com.ex.musicdb.service;

import com.ex.musicdb.model.entities.AlbumEntity;
import com.ex.musicdb.model.servise.AlbumServiceModel;
import com.ex.musicdb.model.view.AlbumCardViewModel;
import com.ex.musicdb.model.view.AlbumViewModel;

import java.util.List;

public interface AlbumService {

    void createAlbum(AlbumServiceModel serviceModel);

    AlbumViewModel findById(Long id);

    AlbumEntity findEntityById(Long albumId);

    List<AlbumCardViewModel> findAll();
}
