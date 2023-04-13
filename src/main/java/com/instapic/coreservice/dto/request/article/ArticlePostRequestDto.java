package com.instapic.coreservice.dto.request.article;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ArticlePostRequestDto {
    private String location;
    private String text;
}
