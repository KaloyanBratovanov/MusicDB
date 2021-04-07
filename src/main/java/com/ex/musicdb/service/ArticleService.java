package com.ex.musicdb.service;

import com.ex.musicdb.model.view.ArticleViewModel;

import java.util.List;

public interface ArticleService {

    List<ArticleViewModel> findAllArticles();
}
