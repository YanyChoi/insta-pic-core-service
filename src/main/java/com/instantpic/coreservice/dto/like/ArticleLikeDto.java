package com.instantpic.coreservice.dto.like;

public class ArticleLikeDto {
    private int articleId;
    private String userId;

    public ArticleLikeDto() {
        articleId = 0;
        userId = "";
    }

    public ArticleLikeDto(int articleId, String userId) {
        this.articleId = articleId;
        this.userId = userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {this.userId = userId;}
}
