package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Follow;
import com.instapic.coreservice.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowCustomRepository {
    int countByUser(UserInfo userInfo);
    int countByTarget(UserInfo target);

}
