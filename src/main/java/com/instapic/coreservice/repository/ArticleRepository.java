package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository {
}
