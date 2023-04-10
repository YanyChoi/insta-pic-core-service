package com.instapic.coreservice.service;

import com.instapic.coreservice.dto.comment.CommentDto;
import com.instapic.coreservice.dto.comment.CommentList;
import com.instapic.coreservice.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto postComment(CommentDto comment) {
        CommentDto result = commentRepository.postComment(comment).get();
        if (comment.getMentionedId() != null && !comment.getMentionedId().isEmpty()) {
            CommentDto mention = commentRepository.postCommentMention(result.getCommentId(), comment.getMentionedId()).get();
            result.setMentionedId(mention.getMentionedId());
        }
        return result;
    }

    public CommentList deleteComment(Optional<Integer> commentId, Optional<Integer> articleId) {
        CommentList result = new CommentList();
        if (commentId.isPresent()) {
            result.setComments(commentRepository.deleteCommentByCommentId(commentId.get().intValue()));
        }
        else if (articleId.isPresent()) {
            result.setComments(commentRepository.deleteCommentByArticleId(articleId.get().intValue()));
        }
        result.setCount(result.getComments().size());
        return result;
    }

    public CommentList getComments(int articleId) {
        CommentList result = new CommentList();
        List<CommentDto> rootComments = commentRepository.getRootCommentsByArticleId(articleId);
        rootComments.forEach(comment ->  {
            List<CommentDto> childComments = commentRepository.getChildCommentsByCommentId(comment.getCommentId());
            comment.setChildComments(childComments);
            comment.setChildCount(childComments.size());
        });
        result.setComments(rootComments);
        result.setCount(result.getComments().size());
        return result;
    }
}
