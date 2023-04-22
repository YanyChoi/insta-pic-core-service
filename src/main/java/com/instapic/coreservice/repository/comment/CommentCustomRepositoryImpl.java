package com.instapic.coreservice.repository.comment;

import com.instapic.coreservice.domain.Comment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.instapic.coreservice.domain.QComment.comment;

@Repository
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Comment> findCommentsWithArticleId(Long articleId, Optional<Long> lastCommentId, int size) {
        if (lastCommentId.isPresent()) {
            return jpaQueryFactory.select(comment)
                    .from(comment)
                    .where(
                        comment.commentId.gt(lastCommentId.get())
                        .and(comment.article.articleId.eq(articleId))
                    )
                    .limit(size)
                    .fetch();
        }
        return jpaQueryFactory.select(comment)
                .from(comment)
                .where(comment.article.articleId.eq(articleId))
                .limit(size)
                .fetch();
    }
}
