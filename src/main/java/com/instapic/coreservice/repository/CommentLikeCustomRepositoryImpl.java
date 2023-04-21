package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.UserInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.instapic.coreservice.domain.like.QCommentLike.commentLike;

@Repository
@RequiredArgsConstructor
public class CommentLikeCustomRepositoryImpl implements CommentLikeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public void deleteCommentLikeByUserIdAndCommentId(Long userId, Long commentId) {
        jpaQueryFactory.delete(commentLike)
                .where(commentLike.comment.commentId.eq(commentId)
                        .and(commentLike.user.userId.eq(userId)))
                .execute();
    }

    @Override
    public List<UserInfo> findCommentLikesByCommentId(Long commentId, Long lastUserId, int size) {
        return jpaQueryFactory.select(commentLike.user)
                .from(commentLike)
                .where(commentLike.comment.commentId.eq(commentId)
                        .and(commentLike.user.userId.gt(lastUserId)))
                .limit(size)
                .fetch();
    }
}
