package com.instapic.coreservice.dto.response.article;

import com.instapic.coreservice.dto.response.user.UserPreviewResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
public class ArticlePreviewResponseDto {
    private Long articleId;
    private UserPreviewResponseDto author;
    private String thumbnail;
    private Long likeCount;
    private Long commentCount;
    private String createdAt;
    private String updatedAt;
}
