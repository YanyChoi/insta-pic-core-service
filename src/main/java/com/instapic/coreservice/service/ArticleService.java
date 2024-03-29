package com.instapic.coreservice.service;

import com.instapic.coreservice.dto.article.ArticleDto;
import com.instapic.coreservice.dto.article.ArticleList;
import com.instapic.coreservice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleDto postArticle(ArticleDto article) {
        ArticleDto result = articleRepository.postArticle(article).get();
        return result;
    }

    public ArticleList getFeedArticlesByUserId(String feedUserId) {
        ArticleList articleList = articleRepository.getFeedArticlesByUserId(feedUserId);
        articleList.setCount(articleList.getArticleList().size());
        return articleList;
    }

    public ArticleList getArticleListByUserId(String userId) {
        ArticleList articleList = articleRepository.getArticleListByUserId(userId);
        articleList.setCount(articleList.getArticleList().size());
        return articleList;
    }

    public ArticleList getArticleListByLocation(String location) {
        ArticleList articleList = articleRepository.getArticleListByLocation(location);
        articleList.setCount(articleList.getArticleList().size());
        return articleList;
    }

    public ArticleDto getArticleById(int articleId) {
        ArticleDto article = articleRepository.getArticleById(articleId).get();
        return article;
    }


    public ArticleDto deleteArticle(int articleId) {
        ArticleDto result = articleRepository.deleteArticle(articleId).get();
        return result;
    }

}
