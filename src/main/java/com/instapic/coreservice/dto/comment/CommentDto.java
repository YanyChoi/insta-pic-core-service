package com.instapic.coreservice.dto.comment;

import java.util.ArrayList;
import java.util.List;

public class CommentDto {
    private int commentId;
    private int articleId;
    private String userId;
    private String profilePic;
    private String mentionedId;
    private String text;
    private String datetime;
    private List<CommentDto> childComments;
    private int childCount;
    private int parentCommentId;


    public CommentDto() {
        commentId = 0;
        articleId = 0;
        userId = "";
        mentionedId = null;
        text = "";
        datetime = "";
        parentCommentId = 0;
        childComments = new ArrayList<CommentDto>();
        childCount = 0;
        profilePic = "";
    }


    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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

    public String getMentionedId() {
        return mentionedId;
    }

    public void setMentionedId(String mentionedId) {
        this.mentionedId = mentionedId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(int parentCommentId) {
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
