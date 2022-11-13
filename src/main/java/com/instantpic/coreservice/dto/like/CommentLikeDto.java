package com.instantpic.coreservice.dto.like;

public class CommentLikeDto {
    private int commentId;
    private String userId;

    public CommentLikeDto(){
        commentId = 0;
        userId = "";
    }

    public CommentLikeDto(int commentId, String userId)
    {
        this.commentId = commentId;
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
