package com.instapic.coreservice.controller;

import com.instapic.coreservice.dto.like.ArticleLikeDto;
import com.instapic.coreservice.dto.like.ArticleLikeList;
import com.instapic.coreservice.dto.like.CommentLikeDto;
import com.instapic.coreservice.dto.like.CommentLikeList;
import com.instapic.coreservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/like")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/article")
    public ArticleLikeDto postArticleLike(int articleId, String userId) {
        ArticleLikeDto result = likeService.postArticleLike(articleId, userId);
        return result;
    }

    @GetMapping("/article")
    public ArticleLikeList getArticleLikes(int articleId) {
        ArticleLikeList result = likeService.getArticleLikes(articleId);
        return result;
    }

    @DeleteMapping("/article")
    public ArticleLikeList deleteArticleLikes(Optional<Integer> articleId, String userId) {
        ArticleLikeList result = likeService.deleteArticleLikes(articleId, userId);
        return result;
    }

    @PostMapping("/comment")
    public CommentLikeDto postCommentLike(int commentId, String userId) {
        CommentLikeDto result = likeService.postCommentLike(commentId, userId);
        return result;
    }

    @GetMapping("/comment")
    public CommentLikeList getCommentLikes(int commentId) {
        CommentLikeList result = likeService.getCommentLikes(commentId);
        return result;
    }
    @DeleteMapping("/comment")
    public CommentLikeList deleteCommentLikes(Optional<Integer> commentId, String userId) {
        CommentLikeList result = likeService.deleteCommentLikes(commentId, userId);
        return result;
    }
}
