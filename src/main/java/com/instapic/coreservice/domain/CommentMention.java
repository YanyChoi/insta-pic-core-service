package com.instapic.coreservice.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "comment_mention")
public class CommentMention extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentMentionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo user;

    public CommentMention() {
    }
    @Builder
    public CommentMention(Comment comment, UserInfo user) {
        this.comment = comment;
        this.user = user;
    }
}
