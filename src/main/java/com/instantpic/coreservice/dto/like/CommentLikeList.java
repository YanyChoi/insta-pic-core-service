package com.instantpic.coreservice.dto.like;

import com.instantpic.coreservice.dto.follow.FollowDto;

import java.util.ArrayList;
import java.util.List;

public class CommentLikeList {
    private List<com.instantpic.coreservice.dto.like.CommentLikeDto> CommentLikeList;
    private int count;

    public CommentLikeList() {
        CommentLikeList = new ArrayList<com.instantpic.coreservice.dto.like.CommentLikeDto>();
        count = 0;
    }

    public List<com.instantpic.coreservice.dto.like.CommentLikeDto> getCommentLikeList() {
        return CommentLikeList;
    }

    public void setCommentLikeList(List<com.instantpic.coreservice.dto.like.CommentLikeDto> CommentLikeList) {this.CommentLikeList = CommentLikeList;}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
