package com.instapic.coreservice.repository.commentLike;

import com.instapic.coreservice.domain.like.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long>, CommentLikeCustomRepository {
}
