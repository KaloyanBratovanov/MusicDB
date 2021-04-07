package com.ex.musicdb.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticleController {


    @GetMapping("/all")
    public String allArticles(Model model) {

        return "all-articles";
    }

    @GetMapping("/add")
    public String addArticle(){
        return "add-article";
    }

}
