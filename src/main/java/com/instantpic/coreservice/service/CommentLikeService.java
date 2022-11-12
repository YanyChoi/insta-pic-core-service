package com.instantpic.coreservice.service;

import com.instantpic.coreservice.dto.like.CommentLikeDto;
import com.instantpic.coreservice.dto.like.CommentLikeList;
import com.instantpic.coreservice.repository.CommentLikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentLikeService {
    private CommentLikeRepository commentLikeRepository;

    public CommentLikeDto postCommentLike(CommentLikeDto commentLike)
    {
        CommentLikeDto result = commentLikeRepository.postCommentLike(commentLike).get();
        return result;
    }

    public CommentLikeList getCommentLikeListByCommentId(String commentId)
    {
        CommentLikeList commentlikeList = commentLikeRepository.getCommentLikeListByCommentId(commentId);
        commentlikeList.setCount(commentlikeList.getCommentLikeList().size());
        return commentlikeList;
    }

    public CommentLikeDto deleteCommentLike(int commentId, String userId)
    {
        CommentLikeDto result = commentLikeRepository.deleteCommentLike(commentId, userId).get();
        return result;
    }
}
