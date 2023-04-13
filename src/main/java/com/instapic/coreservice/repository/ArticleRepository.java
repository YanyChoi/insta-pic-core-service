package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.User;
import com.instapic.coreservice.repository.ArticleCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository {
    Page<Article> findArticlesByAuthorIn(List<User> authors);
}
