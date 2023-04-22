package com.instapic.coreservice.repository.comment;

import com.instapic.coreservice.domain.CommentMention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentMentionRepository extends JpaRepository<CommentMention, Long> {
}
