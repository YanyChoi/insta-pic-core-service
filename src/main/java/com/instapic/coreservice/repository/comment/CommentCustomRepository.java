package com.instapic.coreservice.repository.comment;

import com.instapic.coreservice.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentCustomRepository {

    List<Comment> findCommentsWithArticleId(Long articleId, Optional<Long> lastCommentId, int size);
}
