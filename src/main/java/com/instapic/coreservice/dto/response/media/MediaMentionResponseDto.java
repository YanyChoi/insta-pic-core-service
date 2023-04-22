package com.instapic.coreservice.dto.response.media;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class MediaMentionResponseDto {
    private Long mediaMentionId;
    private float xPosition;
    private float yPosition;
    private Long userId;
    private String username;
    private String createdAt;
    private String updatedAt;
}
