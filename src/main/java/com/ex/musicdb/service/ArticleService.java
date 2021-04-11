package com.ex.musicdb.service;

import com.ex.musicdb.model.servise.ArticleServiceModel;
import com.ex.musicdb.model.view.ArticleViewModel;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    Optional<ArticleViewModel> findLatestArticle();

    List<ArticleViewModel> findAllArticles();

    void createArticle(ArticleServiceModel articleServiceModel);


    ArticleViewModel findById(Long id);

}
