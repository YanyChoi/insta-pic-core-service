package com.instapic.coreservice.dto.follow;

import java.util.ArrayList;
import java.util.List;

public class FollowList {
    private List<FollowDto> followList;
    private int count;

    public FollowList() {
        followList = new ArrayList<FollowDto>();
        count = 0;
    }

    public List<FollowDto> getFollowList() {
        return followList;
    }

    public void setFollowList(List<FollowDto> followList) {
        this.followList = followList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
