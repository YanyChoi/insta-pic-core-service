package com.instapic.coreservice.dto.like;
import java.util.ArrayList;
import java.util.List;

public class ArticleLikeList {
    private List<ArticleLikeDto> ArticleLikeList;
    private int count;

    public ArticleLikeList() {
        ArticleLikeList = new ArrayList<ArticleLikeDto>();
        count = 0;
    }

    public List<ArticleLikeDto> getArticleLikeList() {
        return ArticleLikeList;
    }

    public void setArticleLikeList(List<ArticleLikeDto> ArticleLikeList) {this.ArticleLikeList = ArticleLikeList;}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
