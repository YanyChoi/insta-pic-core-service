package com.instapic.coreservice.repository.articleLike;

import com.instapic.coreservice.domain.UserInfo;

import java.util.List;
import java.util.Optional;

public interface ArticleLikeCustomRepository {
    void deleteArticleLikeByUserIdAndArticleId(Long userId, Long articleId);
    List<UserInfo> findArticleLikesByArticleId(Long articleId, Optional<Long> lastUserId, int size);
    boolean isArticleLikedByUser(Long articleId, Long userId);
}
