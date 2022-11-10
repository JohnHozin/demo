package com.example.demo.controller;

import com.example.demo.entity.ArticleEntity;
import com.example.demo.repository.ArticleRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Base64;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleRepo articleRepo;

    @PostMapping("/addArticle")
    public String addArticle(@ModelAttribute ArticleEntity article) {
        if (articleRepo.findByTitle(article.getTitle()) != null) return "Такое уже есть";
        String html = article.getContent();
        Document document = Jsoup.parse(html);
        Elements images = document.select("img");
        Element img = images.first();    // images.get(0)
        String src = img.attr("src");
        String base64 = src.split(",")[1];
        byte[] image = Base64.getDecoder().decode(base64);
        try {
            FileOutputStream fos = new FileOutputStream("C://java/test.jpg");
            fos.write(image);
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //articleRepo.save(article);
        return "Ok";
    }

    @GetMapping("/getArticles")
    public ResponseEntity getArticles() {
        ArrayList<ArticleEntity> articles = (ArrayList<ArticleEntity>) articleRepo.findAll();
        return ResponseEntity.ok(articles);
    }

    @PostMapping("/getArticleById")
    public ResponseEntity getArticleById(@ModelAttribute ArticleEntity article) {
        return ResponseEntity.ok(articleRepo.findById(article.getId()));
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
