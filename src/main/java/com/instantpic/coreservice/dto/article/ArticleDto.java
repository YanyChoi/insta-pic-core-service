package com.instantpic.coreservice.dto.article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ArticleDto {
    private int articleId;
    private String userId;
    private String location;
    private String text;
    private String date;

    private String thumbnail;

    public ArticleDto() {
        articleId = 0;
        date = "0000-00-00 00:00:00";
        location = "";
        text = "";
        userId = "";
        thumbnail = "";
    }

    public ArticleDto(int articleId, String date, String location, String text, String userId, String thumbnail) {
        this.articleId = articleId;
        this.date = date;
        this.location = location;
        this.text = text;
        this.userId = userId;
        this.thumbnail = thumbnail;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}