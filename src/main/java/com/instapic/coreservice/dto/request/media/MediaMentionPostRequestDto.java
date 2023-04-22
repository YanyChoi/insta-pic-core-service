package com.instapic.coreservice.dto.request.media;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@ToString
public class MediaMentionPostRequestDto {

    float x;
    float y;
    Long userId;

    @Builder
    public MediaMentionPostRequestDto(float x, float y, Long userId) {
        this.x = x;
        this.y = y;
        this.userId = userId;
    }
}

