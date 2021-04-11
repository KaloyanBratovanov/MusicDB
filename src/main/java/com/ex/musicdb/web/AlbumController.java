package com.ex.musicdb.web;


import com.ex.musicdb.model.binding.AlbumAddBindingModel;
import com.ex.musicdb.model.binding.CommentAddBindingModel;
import com.ex.musicdb.model.servise.AlbumServiceModel;
import com.ex.musicdb.model.servise.CommentServiceModel;
import com.ex.musicdb.model.view.AlbumViewModel;
import com.ex.musicdb.service.AlbumService;
import com.ex.musicdb.service.ArtistService;
import com.ex.musicdb.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.ZoneId;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final ModelMapper modelMapper;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final CommentService commentService;


    public AlbumController(ModelMapper modelMapper,
                           AlbumService albumService,
                           ArtistService artistService, CommentService commentService) {
        this.modelMapper = modelMapper;
        this.albumService = albumService;
        this.artistService = artistService;
        this.commentService = commentService;
    }

    @ModelAttribute("albumAddBindingModel")
    public AlbumAddBindingModel createAlbumBindingModel() {
        return new AlbumAddBindingModel();
    }

    @GetMapping("/add")
    public String addAlbum(Model model) {

        model.addAttribute("artists", artistService.findAllArtists());

        return "add-album";
    }

    @PostMapping("/add")
    public String addAlbum(@Valid AlbumAddBindingModel albumAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails principal) {


        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("albumAddBindingModel", albumAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.albumAddBindingModel", bindingResult);

            return "redirect:add";
        }


        AlbumServiceModel albumServiceModel = modelMapper.map(
                albumAddBindingModel,
                AlbumServiceModel.class);

        albumServiceModel.setUser(principal.getUsername());

        albumServiceModel.setReleaseDate(albumAddBindingModel
                .getReleaseDate().atStartOfDay(ZoneId.systemDefault()).toLocalDate());

        albumService.createAlbum(albumServiceModel);

        return "redirect:/home";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){

        AlbumViewModel albumViewModel = albumService.findById(id);

        model.addAttribute("album", albumViewModel);

        model.addAttribute("allComments", commentService.findAllComments());

        return "details";
    }


    @ModelAttribute("commentAddBindingModel")
    public CommentAddBindingModel createCommentBindingModel() {
        return new CommentAddBindingModel();
    }

    @PostMapping("/comments/{id}")
    public String details(@Valid CommentAddBindingModel commentAddBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          @PathVariable Long id,
                          Model model,
                          @AuthenticationPrincipal UserDetails principal){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("commentAddBindingModel", commentAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel", bindingResult);

            return "redirect:/";
        }

        model.addAttribute("comments", commentAddBindingModel);

        CommentServiceModel commentServiceModel = modelMapper.map(
                commentAddBindingModel,
                CommentServiceModel.class);

        commentServiceModel.setUser(principal.getUsername());
        commentServiceModel.setAlbumEntity(albumService.findEntityById(id).getName());

        commentService.createAlbum(commentServiceModel);


        return "redirect:/home";
    }



}
