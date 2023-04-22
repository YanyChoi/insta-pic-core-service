package com.instapic.coreservice.domain;

import com.instapic.coreservice.dto.response.media.MediaMentionResponseDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "media_mention")
public class MediaMention extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaMentionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private Media media;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo user;

    private float xPosition;
    private float yPosition;

    public MediaMention() {
    }
    @Builder
    public MediaMention(Media media, UserInfo user, float xPosition, float yPosition) {
        this.media = media;
        this.user = user;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public MediaMentionResponseDto toDto() {
        return MediaMentionResponseDto.builder()
                .mediaMentionId(mediaMentionId)
                .userId(user.getUserId())
                .username(user.getUserName())
                .xPosition(xPosition)
                .yPosition(yPosition)
                .createdAt(getCreatedAt().toString())
                .updatedAt(getUpdatedAt().toString())
                .build();
    }
}
