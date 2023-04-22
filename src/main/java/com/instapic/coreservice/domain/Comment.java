package com.instapic.coreservice.domain;

import com.instapic.coreservice.domain.like.CommentLike;
import com.instapic.coreservice.dto.response.comment.CommentResponseDto;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private UserInfo author;
    private String text;
    private Long likeCount = 0L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE)
    private List<Comment> childComments = new ArrayList<>();

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<CommentMention> mentions = new ArrayList<>();

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CommentLike> likes = new ArrayList<>();

    public Comment() {
    }
    @Builder
    public Comment(Article article, UserInfo author, String text, Comment parentComment) {
        this.article = article;
        this.author = author;
        this.text = text;
        this.parentComment = parentComment;
    }

    public CommentResponseDto toDto() {
        return CommentResponseDto.builder()
                .commentId(commentId)
                .articleId(article.getArticleId())
                .author(author.toPreviewDto())
                .text(text)
                .likeCount(likeCount)
                .mentionedUsers(mentions.stream().map(mention -> mention.getUser().toPreviewDto()).toList())
                .childComments(childComments.stream().map(Comment::toDto).toList())
                .createdAt(getCreatedAt().toString())
                .updatedAt(getUpdatedAt().toString())
                .build();
    }

    public Long increaseLike() {
        return ++likeCount;
    }

    public Long decreaseLike() {
        return --likeCount;
    }
}
