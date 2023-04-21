package com.instapic.coreservice.domain.like;

import com.instapic.coreservice.domain.BaseEntity;
import com.instapic.coreservice.domain.Comment;
import com.instapic.coreservice.domain.UserInfo;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "comment_like")
public class CommentLike extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public CommentLike() {
    }
    @Builder
    public CommentLike(UserInfo user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }
}
