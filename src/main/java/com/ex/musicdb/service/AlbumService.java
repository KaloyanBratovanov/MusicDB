package com.ex.musicdb.service;

import com.ex.musicdb.model.servise.AlbumServiceModel;
import com.ex.musicdb.model.view.AlbumViewModel;

public interface AlbumService {

    void createAlbum(AlbumServiceModel serviceModel);

    AlbumViewModel findById(Long id);
}
