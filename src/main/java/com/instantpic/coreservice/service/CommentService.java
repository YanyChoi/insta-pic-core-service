package com.instantpic.coreservice.service;

import com.instantpic.coreservice.dto.comment.CommentDto;
import com.instantpic.coreservice.dto.comment.CommentList;
import com.instantpic.coreservice.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public CommentDto deleteComment(int commentId) {
        CommentDto result = commentRepository.deleteCommentById(commentId).get();
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
