package com.instapic.coreservice.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "follow")
public class Follow extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private UserInfo target;

    public Follow() {
    }
    @Builder
    public Follow(UserInfo user, UserInfo target) {
        this.user = user;
        this.target = target;
    }
}
