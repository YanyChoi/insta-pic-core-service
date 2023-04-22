package com.instapic.coreservice.repository.follow;

import com.instapic.coreservice.domain.UserInfo;

import java.util.List;

public interface FollowCustomRepository {

    void deleteByUserIdAndTargetId(Long userId, Long targetId);
    List<UserInfo> findTargetsByUserId(Long userId, Long lastUserId, int size);
    List<UserInfo> findUsersByTargetId(Long targetId, Long lastUserId, int size);
    List<UserInfo> findMutualFollowers(Long userId, Long targetId);
}
