package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
