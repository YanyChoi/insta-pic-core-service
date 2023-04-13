package com.instapic.coreservice.domain;

import com.instapic.coreservice.dto.response.media.MediaResponseDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "media")
public class Media extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaId;

    private String url;

    @Enumerated(EnumType.STRING)
    private MediaFormat mediaFormat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    private String thumbnail;

    @OneToMany(mappedBy = "media", cascade = CascadeType.REMOVE)
    private List<MediaMention> mentions;

    public Media() {
    }

    @Builder
    public Media(String url, MediaFormat mediaFormat, Article article, String thumbnail) {
        this.url = url;
        this.mediaFormat = mediaFormat;
        this.article = article;
        this.thumbnail = thumbnail;
    }

    public MediaResponseDto toDto() {
        return MediaResponseDto.builder()
                .mediaId(mediaId)
                .url(url)
                .thumbnail(thumbnail)
                .mediaFormat(mediaFormat)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }
}
