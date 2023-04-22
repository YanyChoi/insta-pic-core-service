package com.instapic.coreservice.repository.comment;

import com.instapic.coreservice.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {

}
