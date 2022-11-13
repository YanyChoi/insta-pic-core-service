package com.instantpic.coreservice.dto.like;

public class ArticleLikeDto {
    private int articleId;
    private String userId;

    private String profilePic;

    public ArticleLikeDto() {
        articleId = 0;
        userId = "";
        profilePic = "";
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
