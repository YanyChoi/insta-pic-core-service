package com.instapic.coreservice.dto.response.article;

import com.instapic.coreservice.dto.response.comment.CommentResponseDto;
import com.instapic.coreservice.dto.response.media.MediaResponseDto;
import com.instapic.coreservice.dto.response.user.UserPreviewResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @Builder
public class ArticleDetailResponseDto {
    private Long articleId;
    private UserPreviewResponseDto author;
    private String location;
    private String text;
    private List<MediaResponseDto> mediaList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
