package com.ex.musicdb.service.impl;

import com.ex.musicdb.model.entities.ArticleEntity;
import com.ex.musicdb.model.entities.LikeEntity;
import com.ex.musicdb.model.entities.UserEntity;
import com.ex.musicdb.model.servise.ArticleServiceModel;
import com.ex.musicdb.model.view.AlbumViewModel;
import com.ex.musicdb.model.view.ArticleViewModel;
import com.ex.musicdb.repository.ArticleRepository;
import com.ex.musicdb.repository.UserRepository;
import com.ex.musicdb.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<ArticleViewModel> findLatestArticle() {

        return articleRepository.findTopByOrderByCreatedOnDesc();
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

    @Override
    public void createArticle(ArticleServiceModel articleServiceModel) {


        ArticleEntity articleEntity = modelMapper.map(articleServiceModel, ArticleEntity.class);

        articleEntity.setCreatedOn(LocalDateTime.now());


        UserEntity creator = userRepository.findByUsername(articleServiceModel.getUser())
                .orElseThrow(() -> new IllegalArgumentException("Creator "+ articleServiceModel.getUser()
                        +" could not by found"));

        articleEntity.setUserEntity(creator);

        articleRepository.save(articleEntity);
    }

    @Override
    public ArticleViewModel findById(Long id) {


        return articleRepository.findById(id).
                map(articleEntity -> {
                    ArticleViewModel articleViewModel = modelMapper
                            .map(articleEntity, ArticleViewModel.class);
                  articleViewModel.setAuthor(articleViewModel.getAuthor());
                    return articleViewModel;

                }).orElseThrow(IllegalArgumentException::new);


    }


}
