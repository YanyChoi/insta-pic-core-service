package com.instapic.coreservice.repository.article;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.UserInfo;

import java.util.List;
import java.util.Optional;

public interface ArticleCustomRepository {

    List<Article> findFeedArticles(Long userId, Optional<Long> lastArticleId, int size);
    List<Article> findUserArticles(Long userId, Optional<Long> lastArticleId, int size);
    List<Article> findLocationArticles(String location, Optional<Long> lastArticleId, int size);

}
