package com.instapic.coreservice.dto.request.media;

import com.instapic.coreservice.domain.MediaFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class MediaPostRequestDto {
    private String url;
    private MediaFormat mediaFormat;
}
