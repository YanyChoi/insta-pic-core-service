package com.instapic.coreservice.domain;

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

    @OneToMany(mappedBy = "media", cascade = CascadeType.REMOVE)
    private List<MediaMention> mentions;

    public Media() {
    }
    @Builder
    public Media(String url, MediaFormat mediaFormat) {
        this.url = url;
        this.mediaFormat = mediaFormat;
    }
}
