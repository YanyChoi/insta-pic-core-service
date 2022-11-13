package com.instantpic.coreservice.dto.like;

public class CommentLikeDto {
    private int commentId;
    private String userId;

    private String profilePic;

    public CommentLikeDto(){
        commentId = 0;
        userId = "";
        profilePic = "";
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
