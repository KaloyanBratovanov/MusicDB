package com.ex.musicdb.service.impl;

import com.ex.musicdb.model.entities.ArtistEntity;
import com.ex.musicdb.model.view.ArticleViewModel;
import com.ex.musicdb.repository.ArticleRepository;
import com.ex.musicdb.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ArticleViewModel> findAllArticles() {
        return articleRepository.findAll().stream()
                .map(articleEntity -> {
                    ArticleViewModel articleViewModel =modelMapper.map(articleEntity, ArticleViewModel.class);
                    articleViewModel.setAuthor(articleEntity.getUserEntity().getUsername());
                    return articleViewModel;
                }).collect(Collectors.toList());
    }
}
