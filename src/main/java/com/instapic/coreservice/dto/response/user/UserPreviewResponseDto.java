package com.instapic.coreservice.dto.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
public class UserPreviewResponseDto {
    private Long userId;
    private String userName;
    private String fullName;
    private String profilePictureUrl;
    private int followingCount;
    private int followedByCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
