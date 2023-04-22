package com.instapic.coreservice.dto.request.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CommentPatchRequestDto {
    private String text;

    @Builder
    public CommentPatchRequestDto(String text) {
        this.text = text;
    }
}
