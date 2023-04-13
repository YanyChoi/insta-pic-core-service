package com.instapic.coreservice.controller;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.dto.article.ArticleDto;
import com.instapic.coreservice.dto.article.ArticleList;
import com.instapic.coreservice.dto.request.article.ArticlePostRequestDto;
import com.instapic.coreservice.dto.response.article.ArticleDetailResponseDto;
import com.instapic.coreservice.dto.response.article.ArticlePreviewResponseDto;
import com.instapic.coreservice.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/user/{userId}/article")
    public ResponseEntity<ArticlePreviewResponseDto> postArticle(@PathVariable Long userId, @RequestBody ArticlePostRequestDto body) {
        return ResponseEntity.ok().body(articleService.createArticle(userId, body));
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<ArticleDetailResponseDto> getArticle(@PathVariable Long articleId) {
        return ResponseEntity.ok().body(articleService.getArticleById(articleId));
    }

    @DeleteMapping("/article/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/user/{userId}/feed")
    public ResponseEntity<Page<ArticleDetailResponseDto>> getArticleFeed(@PathVariable Long userId, @RequestParam int offset, @RequestParam int size) {
        return ResponseEntity.ok().body(articleService.getFeedArticles(userId, offset, size));
    }

    @GetMapping("/user/{userId}/articles")
    public ResponseEntity<Page<ArticleDetailResponseDto>> getUserArticles(@PathVariable Long userId, @RequestParam int offset, @RequestParam int size) {
        return ResponseEntity.ok().body(articleService.getUserArticles(userId, offset, size));
    }

    @GetMapping("/location/{location}/articles")
    public ResponseEntity<Page<ArticleDetailResponseDto>> getLocationArticles(@PathVariable String location, @RequestParam int offset, @RequestParam int size) {
        return ResponseEntity.ok().body(articleService.getLocationArticles(location, offset, size));
    }
}
