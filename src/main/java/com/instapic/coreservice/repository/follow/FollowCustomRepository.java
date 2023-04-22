package com.instapic.coreservice.repository.follow;

import com.instapic.coreservice.domain.UserInfo;

import java.util.List;
import java.util.Optional;

public interface FollowCustomRepository {

    void deleteByUserIdAndTargetId(Long userId, Long targetId);
    List<UserInfo> findTargetsByUserId(Long userId, Optional<Long> lastUserId, int size);
    List<UserInfo> findUsersByTargetId(Long targetId, Optional<Long> lastUserId, int size);
    List<UserInfo> findMutualFollowers(Long userId, Long targetId, Optional<Long> lastUserId, int size);
}
