package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Comment;

import java.util.List;

public interface CommentCustomRepository {

    List<Comment> findCommentsWithArticleId(Long articleId, Long lastCommentId, int size);
}
