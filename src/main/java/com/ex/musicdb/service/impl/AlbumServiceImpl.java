package com.ex.musicdb.service.impl;

import com.ex.musicdb.model.entities.AlbumEntity;
import com.ex.musicdb.model.entities.ArtistEntity;
import com.ex.musicdb.model.entities.UserEntity;
import com.ex.musicdb.model.servise.AlbumServiceModel;
import com.ex.musicdb.model.view.AlbumCardViewModel;
import com.ex.musicdb.model.view.AlbumViewModel;
import com.ex.musicdb.repository.AlbumRepository;
import com.ex.musicdb.repository.UserRepository;
import com.ex.musicdb.service.AlbumService;
import com.ex.musicdb.service.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service


public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ArtistService artistService;

    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper, UserRepository userRepository, ArtistService artistService) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.artistService = artistService;
    }

    @Override
    public void createAlbum(AlbumServiceModel serviceModel) {

        AlbumEntity albumEntity = modelMapper.map(serviceModel, AlbumEntity.class);

        UserEntity creator =userRepository.findByUsername(serviceModel.getUser())
                .orElseThrow(() -> new IllegalArgumentException("Creator "+ serviceModel.getUser()
                        +" could not by found"));

        albumEntity.setUserEntity(creator);


        ArtistEntity artistEntity = artistService.findByName(serviceModel.getArtist());

        albumEntity.setArtistEntity(artistEntity);

        albumRepository.save(albumEntity);

    }

    @Override
    public AlbumViewModel findById(Long id) {

        return  albumRepository.findById(id).
                map(albumEntity -> {
                    AlbumViewModel albumViewModel = modelMapper
                            .map(albumEntity, AlbumViewModel.class);
                    albumViewModel.setArtist(albumEntity.getArtistEntity().getName());
                    return albumViewModel;

                }).orElseThrow(IllegalArgumentException::new);

    }

    @Override
    public AlbumEntity findEntityById(Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<AlbumCardViewModel> findAll() {
        return albumRepository.findAll()
                .stream().map(albumEntity -> {
                    AlbumCardViewModel albumCardViewModel = modelMapper
                            .map(albumEntity, AlbumCardViewModel.class);
                    albumCardViewModel.setArtist(albumEntity.getArtistEntity().getName());
                    return albumCardViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public AlbumEntity findEntityByName(String albumEntity) {
        return albumRepository.findByName(albumEntity);
    }
}
