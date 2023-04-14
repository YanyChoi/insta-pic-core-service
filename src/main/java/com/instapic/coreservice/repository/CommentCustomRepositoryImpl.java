package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.Comment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.instapic.coreservice.domain.QComment.comment;

@Repository
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Comment> findCommentsWithArticleId(Long articleId, Long lastCommentId, int size) {
        return jpaQueryFactory.select(comment)
                .where(comment.commentId.gt(lastCommentId))
                .where(comment.article.articleId.eq(articleId))
                .limit(size)
                .fetch();
    }
}
