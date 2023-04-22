package com.instapic.coreservice.repository.commentLike;

import com.instapic.coreservice.domain.UserInfo;

import java.util.List;
import java.util.Optional;

public interface CommentLikeCustomRepository {
    void deleteCommentLikeByUserIdAndCommentId(Long userId, Long commentId);
    List<UserInfo> findCommentLikesByCommentId(Long commentId, Optional<Long> lastUserId, int size);
}
