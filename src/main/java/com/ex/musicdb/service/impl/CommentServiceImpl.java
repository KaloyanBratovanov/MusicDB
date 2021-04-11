package com.ex.musicdb.service.impl;

import com.ex.musicdb.model.entities.AlbumEntity;
import com.ex.musicdb.model.entities.CommentEntity;
import com.ex.musicdb.model.entities.UserEntity;
import com.ex.musicdb.model.servise.CommentServiceModel;
import com.ex.musicdb.model.view.AlbumCardViewModel;
import com.ex.musicdb.model.view.CommentViewModel;
import com.ex.musicdb.repository.CommentRepository;
import com.ex.musicdb.repository.UserRepository;
import com.ex.musicdb.service.AlbumService;
import com.ex.musicdb.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

   private final ModelMapper modelMapper;
   private final CommentRepository commentRepository;
   private final UserRepository userRepository;
   private final AlbumService albumService;

    public CommentServiceImpl(ModelMapper modelMapper, CommentRepository commentRepository, UserRepository userRepository, AlbumService albumService) {
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.albumService = albumService;
    }


    @Override
    public void createAlbum(CommentServiceModel commentServiceModel) {

        CommentEntity commentEntity = modelMapper.map(commentServiceModel, CommentEntity.class);

        UserEntity creator =userRepository.findByUsername(commentServiceModel.getUser())
                .orElseThrow(() -> new IllegalArgumentException("Creator "+ commentServiceModel.getUser()
                        +" could not by found"));

        commentEntity.setUser(creator);

        AlbumEntity albumEntity = albumService.findEntityByName(commentServiceModel.getAlbumEntity());

        commentEntity.setAlbumEntity(albumEntity);

        commentEntity.setAddedOn(LocalDateTime.now());

        commentRepository.save(commentEntity);

    }

    @Override
    public List<CommentViewModel> findAllComments() {
        return commentRepository.findAll()
                .stream().map(ce -> {
                    CommentViewModel commentViewModel = modelMapper
                            .map(ce, CommentViewModel.class);
                    commentViewModel.setUser(ce.getAlbumEntity().getName());
                    commentViewModel.setAlbumEntity(ce.getAlbumEntity().getName());
                    return commentViewModel;
                }).collect(Collectors.toList());
    }
}
