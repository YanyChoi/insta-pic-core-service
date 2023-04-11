package com.instapic.coreservice.dto.response.media;

import com.instapic.coreservice.domain.MediaFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
public class MediaResponseDto {
    private Long mediaId;
    private String url;
    private MediaFormat mediaFormat;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
