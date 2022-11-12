package com.instantpic.coreservice.dto.like;

import com.instantpic.coreservice.dto.follow.FollowDto;

import java.util.ArrayList;
import java.util.List;

public class ArticleLikeList {
    private List<com.instantpic.coreservice.dto.like.ArticleLikeDto> ArticleLikeList;
    private int count;

    public ArticleLikeList() {
        ArticleLikeList = new ArrayList<com.instantpic.coreservice.dto.like.ArticleLikeDto>();
        count = 0;
    }

    public List<com.instantpic.coreservice.dto.like.ArticleLikeDto> getArticleLikeList() {
        return ArticleLikeList;
    }

    public void setArticleLikeList(List<com.instantpic.coreservice.dto.like.ArticleLikeDto> ArticleLikeList) {this.ArticleLikeList = ArticleLikeList;}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
