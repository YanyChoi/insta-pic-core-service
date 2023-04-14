package com.instapic.coreservice.service;

import com.instapic.coreservice.domain.Follow;
import com.instapic.coreservice.domain.User;
import com.instapic.coreservice.dto.follow.FollowDto;
import com.instapic.coreservice.dto.follow.FollowList;
import com.instapic.coreservice.dto.follow.NeighborList;
import com.instapic.coreservice.dto.response.user.UserPreviewResponseDto;
import com.instapic.coreservice.repository.FollowRepository;
import com.instapic.coreservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public void follow(Long userId, Long targetId) throws NoSuchElementException {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No such user with ID " + userId));
        User target = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No such user with ID " + targetId));
        Follow follow = Follow.builder()
                .user(user)
                .target(target)
                .build();
        followRepository.save(follow);
    }

    public void unFollow(Long userId, Long targetId) throws NoSuchElementException {
        followRepository.deleteByUserIdAndTargetId(userId, targetId);
    }

    public List<UserPreviewResponseDto> getFollowerList(Long userId, Long lastTargetId, int size) {
        return followRepository.findTargetsByUserId(userId, lastTargetId, size).stream().map(User::toPreviewDto).toList();
    }

    public List<UserPreviewResponseDto> getFollowedByList(Long targetId, Long lastUserId, int size) {
        return followRepository.findUsersByTargetId(targetId, lastUserId, size).stream().map(User::toPreviewDto).toList();
    }

    public List<UserPreviewResponseDto> getMutualFollowerList(Long userId, Long targetId) {
        return followRepository.findMutualFollowers(userId, targetId).stream().map(User::toPreviewDto).toList();
    }

}
