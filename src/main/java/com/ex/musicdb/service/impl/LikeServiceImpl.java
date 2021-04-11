package com.ex.musicdb.service.impl;

import com.ex.musicdb.model.entities.LikeEntity;
import com.ex.musicdb.repository.ArticleRepository;
import com.ex.musicdb.repository.LikeRepository;
import com.ex.musicdb.repository.UserRepository;
import com.ex.musicdb.service.LikeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;


    public LikeServiceImpl(LikeRepository likeRepository, UserRepository userRepository, ModelMapper modelMapper, ArticleRepository articleRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
    }


    @Override
    public void likeById(Long id, String username) {


            LikeEntity likeEntity = new LikeEntity();

            likeEntity.setUser(userRepository.findByUsername(username).orElse(null));
            likeEntity.setArticleEntity(articleRepository.getOne(id));

            if (likeEntity.getLikes() == null) {
                likeEntity.setLikes(0);
            }

            likeEntity.setLikes(likeEntity.getLikes() + 1);

            likeRepository.save(likeEntity);







    }
}
