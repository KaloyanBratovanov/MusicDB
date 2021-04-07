package com.ex.musicdb.web;

import com.ex.musicdb.model.entities.AlbumEntity;
import com.ex.musicdb.model.view.AlbumCardViewModel;
import com.ex.musicdb.model.view.AlbumViewModel;
import com.ex.musicdb.repository.AlbumRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/albums")
@RestController
public class AlbumRestController {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;

    public AlbumRestController(AlbumRepository albumRepository, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")
    public ResponseEntity<List<AlbumCardViewModel>> findAll() {

        List<AlbumCardViewModel> albumCardViewModels = albumRepository.findAll().stream()
                .map(albumEntity -> {
                   AlbumCardViewModel albumCardViewModel = modelMapper.map(albumEntity, AlbumCardViewModel.class);
                   albumCardViewModel.setArtist(albumEntity.getArtistEntity().getName());
                   return albumCardViewModel;
                })
                .collect(Collectors.toList());


        return ResponseEntity
                .ok()
                .body(albumCardViewModels);

//        return albumRepository.findAll().stream().map
//                (ae -> modelMapper.map(ae, AlbumViewModel.class)).collect(Collectors.toList());
    }



}
