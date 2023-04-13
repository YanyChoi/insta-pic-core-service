package com.instapic.coreservice.dto.request.media;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class MediaMentionPostRequestDto {

    float xPosition;
    float yPosition;
    Long userId;
}
