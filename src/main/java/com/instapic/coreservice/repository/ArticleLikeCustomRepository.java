package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.UserInfo;

import java.util.List;

public interface ArticleLikeCustomRepository {
    void deleteArticleLikeByUserIdAndArticleId(Long userId, Long articleId);
    List<UserInfo> findArticleLikesByArticleId(Long articleId, Long lastUserId, int size);
}
