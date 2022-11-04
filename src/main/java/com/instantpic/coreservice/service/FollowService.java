package com.instantpic.coreservice.service;

import com.instantpic.coreservice.dto.follow.FollowDto;
import com.instantpic.coreservice.dto.follow.FollowList;
import com.instantpic.coreservice.dto.follow.NeighborList;
import com.instantpic.coreservice.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    @Autowired
    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public FollowDto follow(String userId, String followId) {
        FollowDto result = followRepository.follow(userId, followId).get();
        return result;
    }

    public FollowDto unfollow(String userId, String followId) {
        FollowDto result = followRepository.unfollow(userId, followId).get();
        return result;
    }

    public FollowList getFollowers(String followingId) {
        FollowList result = followRepository.getFollowers(followingId);
        result.setCount(result.getFollowList().size());
        return result;
    }

    public FollowList getFollowing(String userId) {
        FollowList result = followRepository.getFollowing(userId);
        result.setCount(result.getFollowList().size());
        return result;
    }

    public NeighborList getNeighbors(String userId, String followingId) {
        NeighborList result = followRepository.getNeighbors(userId, followingId);
        result.setCount(result.getNeighbors().size());
        return result;
    }

}
