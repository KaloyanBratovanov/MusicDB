package com.ex.musicdb.web;

import com.ex.musicdb.model.binding.ArticleAddBindingModel;
import com.ex.musicdb.model.servise.ArticleServiceModel;
import com.ex.musicdb.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ModelMapper modelMapper;
    private final ArticleService articleService;

    public ArticleController(ModelMapper modelMapper, ArticleService articleService) {
        this.modelMapper = modelMapper;
        this.articleService = articleService;
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
}
