package com.instapic.coreservice.dto.request.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CommentPatchRequestDto {
    private String text;
}
