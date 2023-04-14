package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.User;
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
import static com.instapic.coreservice.domain.QComment.comment;
import static com.instapic.coreservice.domain.QFollow.follow;
import static com.instapic.coreservice.domain.QMedia.media;
import static com.instapic.coreservice.domain.QUser.user;
import static com.instapic.coreservice.domain.like.QArticleLike.articleLike;

@Repository
@Transactional
@RequiredArgsConstructor
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Article> findFeedArticles(Long userId, Long articleId, int size) {
        return jpaQueryFactory.selectDistinct(article)
                .from(follow)
                .join(follow.user, user)
                .join(follow.user.articles, article)
                .join(article.mediaList, media)
                .where(user.userId.eq(userId))
                .where(article.articleId.gt(articleId))
                .limit(size)
                .fetch();
    }

    @Override
    public List<Article> findUserArticles(Long userId, Long articleId, int size) {
        return jpaQueryFactory.selectDistinct(article)
                .from(user)
                .join(user.articles, article)
                .join(article.mediaList, media)
                .where(user.userId.eq(userId))
                .where(article.articleId.gt(articleId))
                .limit(size)
                .fetch();
    }

    @Override
    public List<Article> findLocationArticles(String location, Long articleId, int size) {
        return jpaQueryFactory.selectDistinct(article)
                .from(article)
                .where(article.location.eq(location))
                .where(article.articleId.gt(articleId))
                .limit(size)
                .fetch();
    }

    @Override
    public List<User> findArticleLikeUsers(Long articleId, Long lastUserId, int size) {
        return jpaQueryFactory.select(articleLike.user)
                .from(article)
                .leftJoin(article.likes, articleLike)
                .where(article.articleId.eq(articleId)
                        .and(articleLike.user.userId.gt(lastUserId)))
                .limit(size)
                .fetch();
    }
}
