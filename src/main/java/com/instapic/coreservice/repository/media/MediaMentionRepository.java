package com.instapic.coreservice.repository.media;

import com.instapic.coreservice.domain.MediaMention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaMentionRepository extends JpaRepository<MediaMention, Long> {
}
