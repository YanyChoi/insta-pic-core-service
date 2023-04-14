package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Follow;
import com.instapic.coreservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowCustomRepository {
    Long countByUser(User user);
    Long countByTarget(User target);

}
