package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.User;

import java.util.List;

public interface CommentLikeCustomRepository {
    void deleteCommentLikeByUserIdAndCommentId(Long userId, Long commentId);
    List<User> findCommentLikesByCommentId(Long commentId, Long lastUserId, int size);
}
