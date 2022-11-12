package com.instantpic.coreservice.controller;

import com.instantpic.coreservice.dto.article.ArticleDto;
import com.instantpic.coreservice.dto.article.ArticleList;
import com.instantpic.coreservice.dto.like.ArticleLikeDto;
import com.instantpic.coreservice.dto.like.ArticleLikeList;
import com.instantpic.coreservice.service.ArticleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/like")

public class ArticleLikeController {

    private ArticleLikeService articlelikeService;
    @Autowired
    ArticleLikeController(ArticleLikeService likeService) {
        this.articlelikeService = articlelikeService;
    }

    @PostMapping("/like/articlelike")
    public ArticleLikeDto postArticleLike(@RequestBody ArticleLikeDto newArticleLike) {
        ArticleLikeDto articleLike = articlelikeService.postArticleLike(newArticleLike);
        return articleLike;
    }

    @GetMapping("/list")
    public ArticleLikeList getArticleLikeList(Optional<String> articleId) {
        ArticleLikeList result;
        if (articleId.isPresent()) {
            result = articlelikeService.getArticleLikeListByArticleId(articleId.get());
        }
        else {
            result = new ArticleLikeList();
        }
        return result;
    }

    @DeleteMapping
    public ArticleLikeDto deleteArticleLike(int articleId, String userId) {
        ArticleLikeDto articleLike = articlelikeService.deleteArticleLike(articleId, userId);
        return articleLike;
    }
}