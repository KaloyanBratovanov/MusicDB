package com.ex.musicdb.service.impl;

import com.ex.musicdb.model.entities.AlbumEntity;
import com.ex.musicdb.model.entities.UserEntity;
import com.ex.musicdb.model.servise.AlbumServiceModel;
import com.ex.musicdb.repository.AlbumRepository;
import com.ex.musicdb.repository.UserRepository;
import com.ex.musicdb.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void createAlbum(AlbumServiceModel serviceModel) {

        AlbumEntity albumEntity = modelMapper.map(serviceModel, AlbumEntity.class);

        UserEntity creator =userRepository.findByUsername(serviceModel.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Creator "+ serviceModel.getUsername()
                        +" could not by found"));

        albumEntity.setUserEntity(creator);

        albumRepository.save(albumEntity);


    }
}
