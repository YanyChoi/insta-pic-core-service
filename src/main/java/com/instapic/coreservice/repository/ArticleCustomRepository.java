package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleCustomRepository {

    Page<Article> findFeedArticles(Long userId, Pageable pageable);
    Page<Article> findUserArticles(Long userId, Pageable pageable);
    Page<Article> findLocationArticles(String location, Pageable pageable);
}
