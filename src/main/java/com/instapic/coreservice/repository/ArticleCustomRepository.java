package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.User;

import java.util.List;

public interface ArticleCustomRepository {

    List<Article> findFeedArticles(Long userId, Long lastArticleId, int size);
    List<Article> findUserArticles(Long userId, Long lastArticleId, int size);
    List<Article> findLocationArticles(String location, Long lastArticleId, int size);
    List<User> findArticleLikeUsers(Long articleId, Long lastUserId, int size);

}
