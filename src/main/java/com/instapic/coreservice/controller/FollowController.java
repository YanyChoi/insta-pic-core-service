package com.instapic.coreservice.controller;

import com.instapic.coreservice.dto.follow.FollowDto;
import com.instapic.coreservice.dto.follow.FollowList;
import com.instapic.coreservice.dto.follow.NeighborList;
import com.instapic.coreservice.dto.response.user.UserPreviewResponseDto;
import com.instapic.coreservice.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{userId}")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow/{targetId}")
    public ResponseEntity<Void> follow(@PathVariable Long userId, @PathVariable Long targetId) {
        followService.follow(userId, targetId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/follow/{targetId}")
    public ResponseEntity<Void> unfollow(@PathVariable Long userId, @PathVariable Long targetId) {
        followService.unFollow(userId, targetId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/followers")
    public ResponseEntity<List<UserPreviewResponseDto>> getFollowers(@PathVariable Long userId, @RequestParam Long lastTargetId, @RequestParam int size) {
        List<UserPreviewResponseDto> followers = followService.getFollowerList(userId, lastTargetId, size);
        return ResponseEntity.ok().body(followers);
    }
    @GetMapping("/following")
    public ResponseEntity<List<UserPreviewResponseDto>> getFollowing(@PathVariable Long userId, @RequestParam Long lastUserId, @RequestParam int size) {
        List<UserPreviewResponseDto> followedByList = followService.getFollowedByList(userId, lastUserId, size);
        return ResponseEntity.ok().body(followedByList);
    }
    @GetMapping("/mutual/{targetId}")
    public ResponseEntity<List<UserPreviewResponseDto>> getMutualFollowers(@PathVariable Long userId, @PathVariable Long targetId) {
        List<UserPreviewResponseDto> mutualFollowers = followService.getMutualFollowerList(userId, targetId);
        return ResponseEntity.ok().body(mutualFollowers);
    }
}
