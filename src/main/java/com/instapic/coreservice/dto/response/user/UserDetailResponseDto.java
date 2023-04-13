package com.instapic.coreservice.dto.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @Builder
public class UserDetailResponseDto {
    private Long userId;
    private String userName;
    private String fullName;
    private String profilePictureUrl;
    private String bio;
    private String url;
    private Long followingCount;
    private Long followedByCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
