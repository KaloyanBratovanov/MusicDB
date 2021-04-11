package com.ex.musicdb.web;

import com.ex.musicdb.model.binding.ArticleAddBindingModel;
import com.ex.musicdb.model.servise.ArticleServiceModel;
import com.ex.musicdb.model.view.ArticleViewModel;
import com.ex.musicdb.service.ArticleService;
import com.ex.musicdb.service.LikeService;
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
@RequestMapping("/articles")
public class ArticleController {

    private final ModelMapper modelMapper;
    private final ArticleService articleService;
    private final LikeService likeService;

    public ArticleController(ModelMapper modelMapper, ArticleService articleService, LikeService likeService) {
        this.modelMapper = modelMapper;
        this.articleService = articleService;
        this.likeService = likeService;
    }


    @GetMapping("/all")
    public String allArticles(Model model) {

        model.addAttribute("allArticle", articleService.findAllArticles());

        return "all-articles";
    }

    @GetMapping("/add")
    public String addArticle(){
        return "add-article";
    }

    @ModelAttribute("articleAddBindingModel")
    public ArticleAddBindingModel createBindingModel() {
        return new ArticleAddBindingModel();
    }



    @PostMapping("/add")
    public String addArticle(@Valid ArticleAddBindingModel articleAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails principal) {


        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("articleAddBindingModel", articleAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.articleAddBindingModel", bindingResult);

            return "redirect:add";
        }


        ArticleServiceModel articleServiceModel = modelMapper.map(
                articleAddBindingModel,
                ArticleServiceModel.class);

        articleServiceModel.setUser(principal.getUsername());

        articleService.createArticle(articleServiceModel);

        return "redirect:/home";
    }


    @GetMapping("/like/{id}")
    public String details(@PathVariable Long id,
                          Model model,
                          @AuthenticationPrincipal UserDetails principal){

        ArticleViewModel articleViewModel = articleService.findById(id);

        model.addAttribute("articles", articleViewModel);


        likeService.likeById(id, principal.getUsername());


        return "redirect:/articles/all";
    }
}
