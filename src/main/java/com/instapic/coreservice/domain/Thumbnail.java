package com.instapic.coreservice.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "thumbnail")
public class Thumbnail extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thumbnailId;

    private String url;

    @OneToOne(mappedBy = "thumbnail")
    private Article article;

}
