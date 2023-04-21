package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.UserInfo;

import java.util.List;

public interface CommentLikeCustomRepository {
    void deleteCommentLikeByUserIdAndCommentId(Long userId, Long commentId);
    List<UserInfo> findCommentLikesByCommentId(Long commentId, Long lastUserId, int size);
}
