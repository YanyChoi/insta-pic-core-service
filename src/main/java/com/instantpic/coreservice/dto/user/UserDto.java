package com.instantpic.coreservice.dto.user;

public class UserDto {
    private String userId;
    private String pw;
    private String name;
    private String profilePic;
    private String introduction;
    private String url;

    public UserDto() {
        userId = "";
        pw = "";
        name = "";
        profilePic = "";
        introduction = "";
        url = "";
    }
    public UserDto(String userId, String pw, String name, String profilePic, String introduction, String url) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.profilePic = profilePic;
        this.introduction = introduction;
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

