package com.instapic.coreservice.repository.articleLike;

import com.instapic.coreservice.domain.UserInfo;
import com.instapic.coreservice.domain.like.ArticleLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.instapic.coreservice.domain.like.QArticleLike.articleLike;

@Repository
@RequiredArgsConstructor
public class ArticleLikeCustomRepositoryImpl implements ArticleLikeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void deleteArticleLikeByUserIdAndArticleId(Long userId, Long articleId) {
//        jpaQueryFactory.update(article)
//                        .where(article.articleId.eq(articleId))
//                                .set(article.)
        jpaQueryFactory.delete(articleLike)
                .where(articleLike.article.articleId.eq(articleId)
                        .and(articleLike.user.userId.eq(userId)))
                .execute();
    }

    @Override
    public List<UserInfo> findArticleLikesByArticleId(Long articleId, Optional<Long> lastUserId, int size) {
        if (lastUserId.isPresent()) {
            return jpaQueryFactory.select(articleLike.user)
                    .from(articleLike)
                    .where(articleLike.article.articleId.eq(articleId)
                            .and(articleLike.user.userId.gt(lastUserId.get())))
                    .limit(size)
                    .fetch();
        }
        return jpaQueryFactory.select(articleLike.user)
                .from(articleLike)
                .where(articleLike.article.articleId.eq(articleId))
                .limit(size)
                .fetch();
    }
    @Override
    public boolean isArticleLikedByUser(Long articleId, Long userId) {
        List<ArticleLike> foundLike = jpaQueryFactory.select(articleLike)
                .from(articleLike)
                .where(articleLike.article.articleId.eq(articleId)
                        .and(articleLike.user.userId.eq(userId)))
                .fetch();
        return !foundLike.isEmpty();
    }
}
