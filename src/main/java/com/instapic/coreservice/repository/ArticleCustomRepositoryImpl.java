package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Article;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static com.instapic.coreservice.domain.QArticle.article;
import static com.instapic.coreservice.domain.QFollow.follow;
import static com.instapic.coreservice.domain.QUser.user;

@Repository
@Transactional
@RequiredArgsConstructor
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<Article> findFeedArticles(Long userId, Pageable pageable) {
        List<Article> results = jpaQueryFactory.selectDistinct(article)
                .from(follow)
                .join(follow.user, user)
                .join(follow.user.articles, article)
                .where(user.userId.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(results, pageable, results.size());
    }

    @Override
    public Page<Article> findUserArticles(Long userId, Pageable pageable) {
        List<Article> results = jpaQueryFactory.selectDistinct(article)
                .from(user)
                .join(user.articles, article)
                .where(user.userId.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(results, pageable, results.size());
    }

    @Override
    public Page<Article> findLocationArticles(String location, Pageable pageable) {
        List<Article> results = jpaQueryFactory.selectDistinct(article)
                .from(article)
                .where(article.location.eq(location))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(results, pageable, results.size());
    }

}
