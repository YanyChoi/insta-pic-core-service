package com.instantpic.coreservice.dto.comment;

import java.time.LocalDateTime;

public class CommentDto {
    private int commentId;
    private int articleId;
    private String userId;
    private String text;
    private LocalDateTime date;
    private int parentCommentId;

    public CommentDto() {
        commentId = 0;
        articleId = 0;
        userId = "";
        text = "";
        date = LocalDateTime.now();
        parentCommentId = 0;
    }

    public CommentDto(int commentId, int articleId, String userId, String text, LocalDateTime date, int parentCommentId) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.userId = userId;
        this.text = text;
        this.date = date;
        this.parentCommentId = parentCommentId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(int parentCommentId) {
        this.parentCommentId = parentCommentId;
    }
}

//CREATE TABLE comment(
//		comment_id INT PRIMARY KEY,
//	  article_id INT,
//    user_id VARCHAR(20),
//    text VARCHAR(500),
//    time VARCHAR(15),
//    parent_comment_id INT DEFAULT NULL,
//		FOREIGN KEY (article_id) REFERENCES article(article_id),
//		FOREIGN KEY (user_id) REFERENCES user(user_id)
//);
