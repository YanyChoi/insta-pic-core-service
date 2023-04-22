package com.instapic.coreservice.repository.article;

import com.instapic.coreservice.domain.Article;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static com.instapic.coreservice.domain.QArticle.article;
import static com.instapic.coreservice.domain.QFollow.follow;
import static com.instapic.coreservice.domain.QMedia.media;
import static com.instapic.coreservice.domain.QUserInfo.userInfo;

@Repository
@Transactional
@RequiredArgsConstructor
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Article> findFeedArticles(Long userId, Optional<Long> lastArticleId, int size) {

        if (lastArticleId.isPresent()) {
            return jpaQueryFactory.selectDistinct(article)
                    .from(follow)
                    .leftJoin(follow.user, userInfo)
                    .leftJoin(follow.user.articles, article)
                    .leftJoin(follow.target.articles, article)
                    .leftJoin(article.mediaList, media)
                    .where(userInfo.userId.eq(userId))
                    .where(article.articleId.gt(lastArticleId.get()))
                    .limit(size)
                    .fetch();
        }
        return jpaQueryFactory.selectDistinct(article)
                .from(article)
                .where(article.author.in(
                        jpaQueryFactory.selectDistinct(userInfo)
                            .from(follow)
                            .join(follow.target, userInfo)
                            .where(follow.user.userId.eq(userId))
                )
                .or(article.author.userId.eq(userId)))
                .fetch();
//        return jpaQueryFactory.selectDistinct(article)
//                .from(follow)
//                .leftJoin(follow.user, userInfo)
//                .leftJoin(follow.user.articles, article)
//                .leftJoin(follow.target.articles, article)
//                .leftJoin(article.mediaList, media)
//                .where(follow.user.userId.eq(userId))
//                .limit(size)
//                .fetch();
    }

    @Override
    public List<Article> findUserArticles(Long userId, Optional<Long> lastArticleId, int size) {
        if (lastArticleId.isPresent()) {
            return jpaQueryFactory.selectDistinct(article)
                    .from(article)
                    .leftJoin(article.mediaList, media)
                    .where(article.author.userId.eq(userId))
                    .where(article.articleId.gt(lastArticleId.get()))
                    .limit(size)
                    .fetch();
        }
        return jpaQueryFactory.selectDistinct(article)
                .from(article)
                .leftJoin(article.mediaList, media)
                .where(article.author.userId.eq(userId))
                .limit(size)
                .fetch();
    }

    @Override
    public List<Article> findLocationArticles(String location, Optional<Long> lastArticleId, int size) {
        if (lastArticleId.isPresent()) {
            return jpaQueryFactory.selectDistinct(article)
                    .from(article)
                    .leftJoin(article.mediaList, media)
                    .where(article.location.eq(location))
                    .where(article.articleId.gt(lastArticleId.get()))
                    .limit(size)
                    .fetch();
        }
        return jpaQueryFactory.selectDistinct(article)
                .from(article)
                .leftJoin(article.mediaList, media)
                .where(article.location.eq(location))
                .limit(size)
                .fetch();
    }
}
