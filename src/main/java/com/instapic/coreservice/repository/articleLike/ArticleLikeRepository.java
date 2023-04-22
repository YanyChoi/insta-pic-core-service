package com.instapic.coreservice.repository.articleLike;

import com.instapic.coreservice.domain.like.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long>, ArticleLikeCustomRepository {
}
