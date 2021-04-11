package com.ex.musicdb.web;

import com.ex.musicdb.model.binding.AlbumAddBindingModel;
import com.ex.musicdb.model.binding.CommentAddBindingModel;
import com.ex.musicdb.model.servise.CommentServiceModel;
import com.ex.musicdb.model.view.ArticleViewModel;
import com.ex.musicdb.service.AlbumService;
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

@Controller
@RequestMapping("/comments")
public class CommentController {

//    private final CommentService commentService;
//    private final ModelMapper modelMapper;
//    private final AlbumService albumService;
//
//
//    public CommentController(CommentService commentService, ModelMapper modelMapper, AlbumService albumService) {
//        this.commentService = commentService;
//        this.modelMapper = modelMapper;
//        this.albumService = albumService;
//    }
//
//    @ModelAttribute("commentAddBindingModel")
//    public CommentAddBindingModel createBindingModel() {
//        return new CommentAddBindingModel();
//    }
//
//    @GetMapping("/add")
//    private String addComments(Model model){
//        model.addAttribute("comments", commentService.findAllComments());
//
//        return "redirect:/";
//    }
//
//
//
//    @PostMapping("/add/{id}")
//    public String details(@Valid CommentAddBindingModel commentAddBindingModel,
//                          BindingResult bindingResult,
//                          RedirectAttributes redirectAttributes,
//                          @PathVariable Long id,
//                          Model model,
//                          @AuthenticationPrincipal UserDetails principal){
//
//        if(bindingResult.hasErrors()){
//            redirectAttributes.addFlashAttribute("commentAddBindingModel", commentAddBindingModel);
//            redirectAttributes
//                    .addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel", bindingResult);
//
//            return "redirect:add";
//        }
//
//        model.addAttribute("comments", commentAddBindingModel);
//
//        CommentServiceModel commentServiceModel = modelMapper.map(
//                commentAddBindingModel,
//                CommentServiceModel.class);
//
//        commentServiceModel.setUser(principal.getUsername());
//        commentServiceModel.setAlbumEntity(albumService.findEntityById(id).getName());
//
//        commentService.createAlbum(commentServiceModel);
//
//
//        return "redirect:/home";
//    }
}
