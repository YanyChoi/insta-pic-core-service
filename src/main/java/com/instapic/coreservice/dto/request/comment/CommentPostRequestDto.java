package com.instapic.coreservice.dto.request.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CommentPostRequestDto {
    private Long userId;
    private String text;
}
