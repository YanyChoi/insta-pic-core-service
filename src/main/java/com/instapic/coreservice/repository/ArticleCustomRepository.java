package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.UserInfo;

import java.util.List;

public interface ArticleCustomRepository {

    List<Article> findFeedArticles(Long userId, Long lastArticleId, int size);
    List<Article> findUserArticles(Long userId, Long lastArticleId, int size);
    List<Article> findLocationArticles(String location, Long lastArticleId, int size);
    List<UserInfo> findArticleLikeUsers(Long articleId, Long lastUserId, int size);

}
