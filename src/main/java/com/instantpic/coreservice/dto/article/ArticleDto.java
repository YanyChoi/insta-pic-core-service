package com.instantpic.coreservice.dto.article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ArticleDto {
    private int articleId;
    private String date;
    private String location;
    private String text;
    private String userId;

    public ArticleDto() {
        articleId = 0;
        date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        location = "";
        text = "";
        userId = "";
    }

    public ArticleDto(int articleId, String date, String location, String text, String userId) {
        this.articleId = articleId;
        this.date = date;
        this.location = location;
        this.text = text;
        this.userId = userId;
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
}