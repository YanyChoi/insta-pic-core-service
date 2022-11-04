package com.instantpic.coreservice.dto.comment;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class CommentDto {
    private int commentId;
    private int articleId;
    private String userId;
    private int mentioned_id;
    private String text;
    private String date;
    private List<CommentDto> childComments;
    private int childCount;
    private OptionalInt parentCommentId;

    public CommentDto() {
        commentId = 0;
        articleId = 0;
        userId = "";
        mentioned_id = 0;
        text = "";
        date = "";
        parentCommentId = null;
        childComments = new ArrayList<CommentDto>();
        childCount = 0;
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

    public int getMentioned_id() {
        return mentioned_id;
    }

    public void setMentioned_id(int mentioned_id) {
        this.mentioned_id = mentioned_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public OptionalInt getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(OptionalInt parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public List<CommentDto> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<CommentDto> childComments) {
        this.childComments = childComments;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
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
