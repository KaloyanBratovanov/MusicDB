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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    private String addAlbum (Model model){

        model.addAttribute("artists", artistService.findAllArtist());

        return "add-album";
    }

    @PostMapping("/add")
    public String addAlbum(@Valid AlbumAddBindingModel albumAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails principal){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("albumAddBindingModel", albumAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumAddBindingModel", bindingResult);


            return "redirect:add";
        }


        AlbumServiceModel albumServiceModel = modelMapper.
                map(albumAddBindingModel, AlbumServiceModel.class);

        albumServiceModel.setUser(principal.getUsername());
        albumService.createAlbum(albumServiceModel);


        return "redirect:/home";
    }
}
