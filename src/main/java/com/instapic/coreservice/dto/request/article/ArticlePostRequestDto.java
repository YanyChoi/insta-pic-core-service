package com.instapic.coreservice.dto.request.article;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ArticlePostRequestDto {
    private String location;
    private String text;

    @Builder
    public ArticlePostRequestDto(String location, String text) {
        this.location = location;
        this.text = text;
    }
}
