package com.instapic.coreservice.dto.article;

import java.util.ArrayList;
import java.util.List;

public class ArticleList {

    private List<ArticleDto> articleList;
    private int count;

    public ArticleList() {
        articleList = new ArrayList<ArticleDto>();
        count = 0;
    }

    public List<ArticleDto> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleDto> articleList) {
        this.articleList = articleList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
