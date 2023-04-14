package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.User;

import java.util.List;

public interface ArticleLikeCustomRepository {
    void deleteArticleLikeByUserIdAndArticleId(Long userId, Long articleId);
    List<User> findArticleLikesByArticleId(Long articleId, Long lastUserId, int size);
}
