package com.instapic.coreservice.controller;

import com.instapic.coreservice.dto.request.article.ArticlePostRequestDto;
import com.instapic.coreservice.dto.response.article.ArticleDetailResponseDto;
import com.instapic.coreservice.dto.response.article.ArticlePreviewResponseDto;
import com.instapic.coreservice.dto.response.user.UserPreviewResponseDto;
import com.instapic.coreservice.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<ArticleDetailResponseDto>> getArticleFeed(@PathVariable Long userId, @RequestParam Long lastArticleId, @RequestParam int size) {
        return ResponseEntity.ok().body(articleService.getFeedArticles(userId, lastArticleId, size));
    }

    @GetMapping("/user/{userId}/articles")
    public ResponseEntity<List<ArticlePreviewResponseDto>> getUserArticles(@PathVariable Long userId, @RequestParam Long lastArticleId, @RequestParam int size) {
        return ResponseEntity.ok().body(articleService.getUserArticles(userId, lastArticleId, size));
    }

    @GetMapping("/location/{location}/articles")
    public ResponseEntity<List<ArticlePreviewResponseDto>> getLocationArticles(@PathVariable String location, @RequestParam Long lastArticleId, @RequestParam int size) {
        return ResponseEntity.ok().body(articleService.getLocationArticles(location, lastArticleId, size));
    }

    @GetMapping("/article/{articleId}/likes")
    public ResponseEntity<List<UserPreviewResponseDto>> getArticleLikeUsers(@PathVariable Long articleId, @RequestParam Long lastUserId, @RequestParam int size) {
        return ResponseEntity.ok().body(articleService.getArticleLikes(articleId, lastUserId, size));
    }

    @PostMapping("/article/{articleId}/like")
    public ResponseEntity<Void> postArticleLike(@PathVariable Long articleId, @RequestParam Long userId) {
        articleService.createArticleLike(articleId, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/article/{articleId}/like")
    public ResponseEntity<Void> deleteArticleLike(@PathVariable Long articleId, @RequestParam Long userId) {
        articleService.deleteArticleLike(articleId, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
