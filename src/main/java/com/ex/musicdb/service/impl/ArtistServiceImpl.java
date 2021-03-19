package com.ex.musicdb.service.impl;

import com.ex.musicdb.model.entities.ArtistEntity;
import com.ex.musicdb.repository.ArtistRepository;
import com.ex.musicdb.service.ArtistService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class ArtistServiceImpl implements ArtistService {

    private Resource artistsFile;
    private Gson gson;
    private ArtistRepository artistRepository;

    public ArtistServiceImpl(
            @Value("classpath:init/artists.json")Resource artistsFile,
            Gson gson,
            ArtistRepository artistRepository
            ) {
        this.artistsFile = artistsFile;
        this.gson = gson;
        this.artistRepository = artistRepository;
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
}
