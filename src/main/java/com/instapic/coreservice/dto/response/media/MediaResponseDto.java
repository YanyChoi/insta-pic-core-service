package com.instapic.coreservice.dto.response.media;

import com.instapic.coreservice.domain.MediaFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @Builder
public class MediaResponseDto {
    private Long mediaId;
    private String url;
    private String thumbnail;
    private MediaFormat mediaFormat;
    private List<MediaMentionResponseDto> mentions;
    private String createdAt;
    private String updatedAt;
}
