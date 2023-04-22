package com.instapic.coreservice.repository.commentLike;

import com.instapic.coreservice.domain.UserInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public List<UserInfo> findCommentLikesByCommentId(Long commentId, Optional<Long> lastUserId, int size) {
        if (lastUserId.isPresent()) {
            return jpaQueryFactory.select(commentLike.user)
                    .from(commentLike)
                    .where(commentLike.comment.commentId.eq(commentId)
                            .and(commentLike.user.userId.gt(lastUserId.get())))
                    .limit(size)
                    .fetch();
        }
        return jpaQueryFactory.select(commentLike.user)
                .from(commentLike)
                .where(commentLike.comment.commentId.eq(commentId))
                .limit(size)
                .fetch();
    }
}
