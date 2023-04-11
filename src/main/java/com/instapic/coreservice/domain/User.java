package com.instapic.coreservice.domain;

import com.instapic.coreservice.domain.like.ArticleLike;
import com.instapic.coreservice.domain.like.CommentLike;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String pw;

    private String fullName;

    private String profilePictureUrl;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CommentMention> commentMentions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MediaMention> mediaMentions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ArticleLike> likedArticles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CommentLike> likedComments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Follow> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "target", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Follow> followedByList = new ArrayList<>();


    public User() {
    }
    @Builder
    public User(String userName, String pw, String fullName, String profilePictureUrl) {
        this.userName = userName;
        this.pw = pw;
        this.fullName = fullName;
        this.profilePictureUrl = profilePictureUrl;
    }
}
