package com.instapic.coreservice.repository.article;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.repository.article.ArticleCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository {
}
