package com.instapic.coreservice.dto.response.comment;

import com.instapic.coreservice.dto.response.user.UserPreviewResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @Builder
public class CommentResponseDto {
    private Long commentId;
    private Long articleId;
    private UserPreviewResponseDto user;
    private String text;
    private List<UserPreviewResponseDto> mentionedUsers;
    private List<CommentResponseDto> childComments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
