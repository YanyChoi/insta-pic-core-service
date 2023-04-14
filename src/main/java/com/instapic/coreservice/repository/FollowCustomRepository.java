package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.User;

import java.util.List;

public interface FollowCustomRepository {

    void deleteByUserIdAndTargetId(Long userId, Long targetId);
    List<User> findTargetsByUserId(Long userId, Long lastUserId, int size);
    List<User> findUsersByTargetId(Long targetId, Long lastUserId, int size);
    List<User> findMutualFollowers(Long userId, Long targetId);
}
