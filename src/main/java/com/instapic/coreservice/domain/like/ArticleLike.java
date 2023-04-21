package com.instapic.coreservice.domain.like;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.BaseEntity;
import com.instapic.coreservice.domain.UserInfo;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "article_like")
public class ArticleLike extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    public ArticleLike() {
    }
    @Builder
    public ArticleLike(UserInfo user, Article article) {
        this.user = user;
        this.article = article;
    }
}
