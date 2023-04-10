package com.instapic.coreservice.dto.follow;

import java.util.List;

public class FollowDto {
    private String userId;
    private String followId;

    private String profilePic;

    public FollowDto() {
        userId = "";
        followId = "";
        profilePic = "";
    }

    public FollowDto(String userId, String followId) {
        this.userId = userId;
        this.followId = followId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
