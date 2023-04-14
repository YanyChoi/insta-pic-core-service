package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.instapic.coreservice.domain.like.QArticleLike.articleLike;

@Repository
@RequiredArgsConstructor
public class ArticleLikeCustomRepositoryImpl implements ArticleLikeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void deleteArticleLikeByUserIdAndArticleId(Long userId, Long articleId) {
        jpaQueryFactory.delete(articleLike)
                .where(articleLike.article.articleId.eq(articleId)
                        .and(articleLike.user.userId.eq(userId)))
                .execute();
    }

    @Override
    public List<User> findArticleLikesByArticleId(Long articleId, Long lastUserId, int size) {
        return jpaQueryFactory.select(articleLike.user)
                .from(articleLike)
                .where(articleLike.article.articleId.eq(articleId)
                        .and(articleLike.user.userId.gt(lastUserId)))
                .limit(size)
                .fetch();
    }
}
