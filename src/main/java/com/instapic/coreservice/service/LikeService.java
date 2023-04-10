package com.instapic.coreservice.service;

import com.instapic.coreservice.dto.like.ArticleLikeDto;
import com.instapic.coreservice.dto.like.ArticleLikeList;
import com.instapic.coreservice.dto.like.CommentLikeDto;
import com.instapic.coreservice.dto.like.CommentLikeList;
import com.instapic.coreservice.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public ArticleLikeDto postArticleLike(int articleId, String userId) {
        ArticleLikeDto result = likeRepository.postArticleLike(articleId, userId).get();
        return result;
    }

    public ArticleLikeList getArticleLikes(int articleId) {
        ArticleLikeList result = new ArticleLikeList();
        result.setArticleLikeList(likeRepository.getArticleLikes(articleId));
        result.setCount(result.getArticleLikeList().size());
        return result;
    }

    public ArticleLikeList deleteArticleLikes(Optional<Integer> articleId, String userId) {
        ArticleLikeList result = new ArticleLikeList();

        if (articleId.isPresent() && !userId.isEmpty()) {
            result.setArticleLikeList(likeRepository.deleteArticleLikeByUserIdAndArticleId(articleId.get(), userId));
        }
        else if (articleId.isPresent()) {
            result.setArticleLikeList(likeRepository.deleteArticleLikeByArticleId(articleId.get()));
        }
        result.setCount(result.getArticleLikeList().size());
        return result;
    }

    public CommentLikeDto postCommentLike(int commentId, String userId) {
        CommentLikeDto result = likeRepository.postCommentLike(commentId, userId).get();
        return result;
    }

    public CommentLikeList getCommentLikes(int commentId) {
        CommentLikeList result = new CommentLikeList();
        result.setCommentLikeList(likeRepository.getCommentLikes(commentId));
        result.setCount(result.getCommentLikeList().size());
        return result;
    }

    public CommentLikeList deleteCommentLikes(Optional<Integer> commentId, String userId) {
        CommentLikeList result = new CommentLikeList();
        if (commentId.isPresent() && !userId.isEmpty()) {
            result.setCommentLikeList(likeRepository.deleteCommentLikeByCommentIdAndUserId(commentId.get(), userId));
        }
        else if (commentId.isPresent()) {
            result.setCommentLikeList(likeRepository.deleteCommentLikeByCommentId(commentId.get()));
        }
        result.setCount(result.getCommentLikeList().size());
        return result;
    }
}
