package com.instapic.coreservice.controller;

import com.instapic.coreservice.dto.follow.FollowDto;
import com.instapic.coreservice.dto.follow.FollowList;
import com.instapic.coreservice.dto.follow.NeighborList;
import com.instapic.coreservice.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private FollowService followService;
    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }
    @PostMapping
    public FollowDto follow(String userId, String followId) {
        FollowDto result = followService.follow(userId, followId);
        return result;
    }
    @DeleteMapping
    public FollowDto unfollow(String userId, String followId) {
        FollowDto result = followService.unfollow(userId, followId);
        return result;
    }

    @GetMapping("/followers")
    public FollowList getFollowers(String followingId) {
        FollowList result = followService.getFollowers(followingId);
        return result;
    }
    @GetMapping("/following")
    public FollowList getFollowing(String userId) {
        FollowList result = followService.getFollowing(userId);
        return result;
    }
    @GetMapping("/neighbors")
    public NeighborList getNeighbors(String userId, String followingId) {
        NeighborList result = followService.getNeighbors(userId, followingId);
        return result;
    }
}
