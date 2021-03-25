package com.ex.musicdb.service.impl;

import com.ex.musicdb.model.entities.ArtistEntity;
import com.ex.musicdb.model.view.ArtistViewModel;
import com.ex.musicdb.repository.ArtistRepository;
import com.ex.musicdb.service.ArtistService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final Resource artistsFile;
    private final Gson gson;
    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;

    public ArtistServiceImpl(
            @Value("classpath:init/artists.json") Resource artistsFile,
            Gson gson,
            ArtistRepository artistRepository,
            ModelMapper modelMapper) {
        this.artistsFile = artistsFile;
        this.gson = gson;
        this.artistRepository = artistRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedArtist() {

        if (artistRepository.count() == 0){
            try {
               ArtistEntity[] artistEntities =
                       gson.fromJson(Files.readString(Path.of(artistsFile.getURI())), ArtistEntity[].class);

                Arrays.stream(artistEntities)
                        .forEach(artistRepository::save);

            } catch (IOException e) {
                throw new IllegalStateException("Cannot seed artist");
            }
        }
    }

    @Override
    public ArtistEntity findByName(String artist) {

       return artistRepository.findByName(artist).orElseThrow(IllegalArgumentException::new);

    }

    @Override
    public List<String> findAllArtists() {

        return artistRepository.findAllArtistNames();

    }
}
