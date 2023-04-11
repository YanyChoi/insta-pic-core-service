package com.instapic.coreservice.domain;

import com.instapic.coreservice.domain.like.ArticleLike;
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
    private User author;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thumbnail_id")
    private Thumbnail thumbnail;

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
    public Article(User author, Thumbnail thumbnail, String location, String text) {
        this.author = author;
        this.thumbnail = thumbnail;
        this.location = location;
        this.text = text;
    }
}
