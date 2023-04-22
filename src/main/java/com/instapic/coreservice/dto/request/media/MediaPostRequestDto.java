package com.instapic.coreservice.dto.request.media;

import com.instapic.coreservice.domain.MediaFormat;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@ToString
public class MediaPostRequestDto {
    private MediaFormat mediaFormat;
    @Builder
    public MediaPostRequestDto(MediaFormat mediaFormat, List<MediaMentionPostRequestDto> mentions) {
        this.mediaFormat = mediaFormat;
    }
}

