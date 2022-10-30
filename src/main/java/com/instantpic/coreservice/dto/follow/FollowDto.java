package com.instantpic.coreservice.dto.follow;

import java.util.List;

public class FollowDto {
    private String userId;
    private String followId;

    public FollowDto() {
        userId = "";
        followId = "";
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
}
