package com.ex.musicdb.web;

import com.ex.musicdb.model.binding.AlbumAddBindingModel;
import com.ex.musicdb.model.servise.AlbumServiceModel;
import com.ex.musicdb.service.AlbumService;
import com.ex.musicdb.service.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final ModelMapper modelMapper;
    private final AlbumService albumService;
    private final ArtistService artistService;



    public AlbumController(ModelMapper modelMapper, AlbumService albumService, ArtistService artistService) {
        this.modelMapper = modelMapper;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @ModelAttribute("albumAddBindingModel")
    public AlbumAddBindingModel createBindingModel(){
        return new AlbumAddBindingModel();
    }

    @GetMapping("/add")
    private Model addAlbum (Model model){

        model.addAttribute("artists", artistService.findAllArtist());

        return model;
    }

    @PostMapping("/add")
    public String addAlbum(AlbumAddBindingModel bindingModel,
                           @AuthenticationPrincipal UserDetails principal){

        AlbumServiceModel albumServiceModel = modelMapper.map(bindingModel, AlbumServiceModel.class);

        albumServiceModel.setUsername(principal.getUsername());
        albumService.createAlbum(albumServiceModel);


        return "redirect:/home";
    }
}
