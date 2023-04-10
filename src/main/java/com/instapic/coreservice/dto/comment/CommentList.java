package com.instapic.coreservice.dto.comment;

import java.util.ArrayList;
import java.util.List;

public class CommentList {

    private List<CommentDto> comments;
    private int count;

    public CommentList() {
        comments = new ArrayList<CommentDto>();
        count = 0;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
