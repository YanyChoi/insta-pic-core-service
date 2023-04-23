package com.instapic.coreservice.service;

import com.instapic.coreservice.domain.Follow;
import com.instapic.coreservice.domain.UserInfo;
import com.instapic.coreservice.dto.response.user.UserPreviewResponseDto;
import com.instapic.coreservice.repository.follow.FollowRepository;
import com.instapic.coreservice.repository.user.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {

    private final FollowRepository followRepository;
    private final UserInfoRepository userInfoRepository;

    public void follow(Long userId, Long targetId) throws NoSuchElementException {
        UserInfo userInfo = userInfoRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No such user with ID " + userId));
        UserInfo target = userInfoRepository.findById(targetId).orElseThrow(() -> new NoSuchElementException("No such user with ID " + targetId));
        Follow follow = Follow.builder()
                .user(userInfo)
                .target(target)
                .build();
        followRepository.save(follow);
    }

    public void unFollow(Long userId, Long targetId) throws NoSuchElementException {
        followRepository.deleteByUserIdAndTargetId(userId, targetId);
    }

    public List<UserPreviewResponseDto> getFollowerList(Long userId, Optional<Long> lastTargetId, int size) {
        return followRepository.findTargetsByUserId(userId, lastTargetId, size).stream().map(UserInfo::toPreviewDto).toList();
    }

    public List<UserPreviewResponseDto> getFollowedByList(Long targetId, Optional<Long> lastUserId, int size) {
        return followRepository.findUsersByTargetId(targetId, lastUserId, size).stream().map(UserInfo::toPreviewDto).toList();
    }

    public List<UserPreviewResponseDto> getMutualFollowerList(String username, Long targetId, Optional<Long> lastUserId, int size) {
        return followRepository.findMutualFollowers(username, targetId, lastUserId, size).stream().map(UserInfo::toPreviewDto).toList();
    }

}
