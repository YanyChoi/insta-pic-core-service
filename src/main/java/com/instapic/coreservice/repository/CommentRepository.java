package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Comment;
import com.instapic.coreservice.repository.CommentCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {

}
