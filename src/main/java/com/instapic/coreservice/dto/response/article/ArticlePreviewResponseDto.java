package com.instapic.coreservice.dto.response.article;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
public class ArticlePreviewResponseDto {
    private Long articleId;
    private String thumbnail;
    private int likeCount;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
