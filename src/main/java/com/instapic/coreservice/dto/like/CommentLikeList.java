package com.instapic.coreservice.dto.like;

import java.util.ArrayList;
import java.util.List;

public class CommentLikeList {
    private List<CommentLikeDto> CommentLikeList;
    private int count;

    public CommentLikeList() {
        CommentLikeList = new ArrayList<CommentLikeDto>();
        count = 0;
    }

    public List<CommentLikeDto> getCommentLikeList() {
        return CommentLikeList;
    }

    public void setCommentLikeList(List<CommentLikeDto> CommentLikeList) {this.CommentLikeList = CommentLikeList;}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
