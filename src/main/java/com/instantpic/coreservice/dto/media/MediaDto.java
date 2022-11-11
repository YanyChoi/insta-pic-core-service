package com.instantpic.coreservice.dto.media;

public class MediaDto {
    private int mediaId;
    private int articleId;
    private String url;

    public MediaDto() {
        mediaId = 0;
        articleId = 0;
        url = "";
    }

    public MediaDto(int mediaId, int articleId, String url) {
        this.mediaId = mediaId;
        this.articleId = articleId;
        this.url = url;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

//CREATE TABLE media(
//		media_id INT PRIMARY KEY,
//    article_id INT,
//    url VARCHAR(200),
//		FOREIGN KEY (article_id) REFERENCES article(article_id)
//);