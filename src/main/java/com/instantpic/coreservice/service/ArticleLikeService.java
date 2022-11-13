package com.instantpic.coreservice.service;

import com.instantpic.coreservice.dto.like.ArticleLikeDto;
import com.instantpic.coreservice.dto.like.ArticleLikeList;
import com.instantpic.coreservice.repository.ArticleLikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleLikeService {
    private ArticleLikeRepository articleLikeRepository;

    public ArticleLikeDto postArticleLike(ArticleLikeDto articleLike)
    {
        ArticleLikeDto result = articleLikeRepository.postArticleLike(articleLike).get();
        return result;
    }

    public ArticleLikeList getArticleLikeListByArticleId(String articleId)
    {
        ArticleLikeList articlelikeList = articleLikeRepository.getArticleLikeListByArticleId(articleId);
        articlelikeList.setCount(articlelikeList.getArticleLikeList().size());
        return articlelikeList;
    }

    public ArticleLikeDto deleteArticleLike(int articleId, String userId)
    {
        ArticleLikeDto result = articleLikeRepository.deleteArticleLike(articleId, userId).get();
        return result;
    }
}
