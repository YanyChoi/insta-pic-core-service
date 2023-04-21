package com.instapic.coreservice.domain;

import com.instapic.coreservice.domain.like.ArticleLike;
import com.instapic.coreservice.dto.response.article.ArticleDetailResponseDto;
import com.instapic.coreservice.dto.response.article.ArticlePreviewResponseDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "article")
public class Article extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private UserInfo author;

    private String location;

    private String text;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private final List<Media> mediaList = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private final List<ArticleLike> likes = new ArrayList<>();

    public Article() {
    }
    @Builder
    public Article(UserInfo author, String location, String text) {
        this.author = author;
        this.location = location;
        this.text = text;
    }

    public ArticleDetailResponseDto toDetailedDto() {
        return ArticleDetailResponseDto.builder()
                .articleId(articleId)
                .author(author.toPreviewDto())
                .location(location)
                .text(text)
                .mediaList(mediaList.stream().map(Media::toDto).toList())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }

    public ArticlePreviewResponseDto toPreviewDto() {
        return ArticlePreviewResponseDto.builder()
                .articleId(articleId)
                .author(author.toPreviewDto())
                .likeCount(likes.size())
                .commentCount(comments.size())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }
}
