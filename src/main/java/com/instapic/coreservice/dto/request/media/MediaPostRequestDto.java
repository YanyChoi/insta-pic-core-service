package com.instapic.coreservice.dto.request.media;

import com.instapic.coreservice.domain.MediaFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Builder
public class MediaPostRequestDto {
    private MediaFormat mediaFormat;
    private List<MediaMentionPostRequestDto> mentions;
}

