package com.instapic.coreservice.dto.request.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CommentPostRequestDto {
    private Long userId;
    private String text;
    private Long parentCommentId;

    @Builder
    public CommentPostRequestDto(Long userId, String text, Long parentCommentId) {
        this.userId = userId;
        this.text = text;
        this.parentCommentId = parentCommentId;
    }
}
