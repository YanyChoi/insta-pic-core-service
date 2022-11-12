package com.instantpic.coreservice.controller;

import com.instantpic.coreservice.dto.article.ArticleDto;
import com.instantpic.coreservice.dto.article.ArticleList;
import com.instantpic.coreservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ArticleDto postArticle(@RequestBody ArticleDto newArticle) {
        ArticleDto article = articleService.postArticle(newArticle);
        return article;
    }

    @GetMapping("/list")
    public ArticleList getArticleList(Optional<String> userId, Optional<String> feedUserId, Optional<String> location) {
        ArticleList result;
        if (userId.isPresent()) {
            result = articleService.getArticleListByUserId(userId.get());
        }
        else if (feedUserId.isPresent()) {
            result = articleService.getFeedArticlesByUserId(feedUserId.get());
        }
        else if (location.isPresent()) {
            result = articleService.getArticleListByLocation(location.get());
        }
        else {
            result = new ArticleList();
        }
        return result;
    }

    @GetMapping
    public ArticleDto getArticle(int articleId) {
        ArticleDto article = articleService.getArticleById(articleId);
        return article;
    }

    @DeleteMapping
    public ArticleDto deleteArticle(int articleId) {
        ArticleDto article = articleService.deleteArticle(articleId);
        return article;
    }
}
