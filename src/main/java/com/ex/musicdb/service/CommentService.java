package com.ex.musicdb.service;

import com.ex.musicdb.model.servise.CommentServiceModel;
import com.ex.musicdb.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {

    void createAlbum(CommentServiceModel commentServiceModel);

    List<CommentViewModel> findAllComments();
}
