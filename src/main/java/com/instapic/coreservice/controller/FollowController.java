package com.instapic.coreservice.controller;

import com.instapic.coreservice.dto.response.user.UserPreviewResponseDto;
import com.instapic.coreservice.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/user/{userId}/follow/{targetId}")
    public ResponseEntity<Void> follow(@PathVariable Long userId, @PathVariable Long targetId) {
        followService.follow(userId, targetId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/user/{userId}/follow/{targetId}")
    public ResponseEntity<Void> unfollow(@PathVariable Long userId, @PathVariable Long targetId) {
        followService.unFollow(userId, targetId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/user/{userId}/followers")
    public ResponseEntity<List<UserPreviewResponseDto>> getFollowers(@PathVariable Long userId, @RequestParam(required = false) Optional<Long> lastTargetId, @RequestParam int size) {
        List<UserPreviewResponseDto> followers = followService.getFollowerList(userId, lastTargetId, size);
        return ResponseEntity.ok().body(followers);
    }
    @GetMapping("/user/{userId}/following")
    public ResponseEntity<List<UserPreviewResponseDto>> getFollowing(@PathVariable Long userId, @RequestParam(required = false) Optional<Long> lastUserId, @RequestParam int size) {
        List<UserPreviewResponseDto> followedByList = followService.getFollowedByList(userId, lastUserId, size);
        return ResponseEntity.ok().body(followedByList);
    }
    @GetMapping("/mutual/{targetId}")
    public ResponseEntity<List<UserPreviewResponseDto>> getMutualFollowers(@AuthenticationPrincipal User user, @PathVariable Long targetId, @RequestParam(required = false) Optional<Long> lastUserId, @RequestParam int size) {
        List<UserPreviewResponseDto> mutualFollowers = followService.getMutualFollowerList(user.getUsername(), targetId, lastUserId, size);
        return ResponseEntity.ok().body(mutualFollowers);
    }
}
