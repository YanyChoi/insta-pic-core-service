package com.instapic.coreservice.service;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.Comment;
import com.instapic.coreservice.domain.CommentMention;
import com.instapic.coreservice.domain.UserInfo;
import com.instapic.coreservice.domain.like.CommentLike;
import com.instapic.coreservice.dto.request.comment.CommentPostRequestDto;
import com.instapic.coreservice.dto.response.comment.CommentResponseDto;
import com.instapic.coreservice.dto.response.user.UserPreviewResponseDto;
import com.instapic.coreservice.repository.article.ArticleRepository;
import com.instapic.coreservice.repository.comment.CommentMentionRepository;
import com.instapic.coreservice.repository.commentLike.CommentLikeRepository;
import com.instapic.coreservice.repository.user.UserInfoRepository;
import com.instapic.coreservice.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserInfoRepository userInfoRepository;
    private final ArticleRepository articleRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentMentionRepository commentMentionRepository;
    public void createComment(CommentPostRequestDto dto, Long articleId, List<String> mentions) throws NoSuchElementException {
        UserInfo author = userInfoRepository.findById(dto.getUserId()).orElseThrow(() -> new NoSuchElementException("No such user with ID " + dto.getUserId()));
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new NoSuchElementException("No such article with ID " + articleId));
        Comment comment = Comment.builder()
                .article(article)
                .author(author)
                .text(dto.getText())
                .build();
        commentRepository.save(comment);
        commentMentionRepository.saveAll(mentions.stream().map(mentionString -> {
            UserInfo mentionedUser = userInfoRepository.findByUserName(mentionString)
                    .orElseThrow(() -> new NoSuchElementException("No such user with ID " + dto.getUserId()));
            return CommentMention.builder()
                    .comment(comment)
                    .user(mentionedUser)
                    .build();
        }).toList());
    }

    public List<CommentResponseDto> getCommentsByArticle(Long articleId, Optional<Long> lastCommentId, int size) {
        return commentRepository.findCommentsWithArticleId(articleId, lastCommentId, size)
                .stream().map(Comment::toDto)
                .toList();
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
    public void createCommentLike(Long commentId, Long userId) throws NoSuchElementException {
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("No such user with ID " + userId));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("No such comment with ID " + commentId));
        CommentLike commentLike = CommentLike.builder()
                .comment(comment)
                .user(userInfo)
                .build();
        commentLikeRepository.save(commentLike);
        comment.increaseLike();
    }
    public void deleteCommentLike(Long commentId, Long userId) throws NoSuchElementException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("No such comment with ID " + commentId));
        comment.decreaseLike();
        commentLikeRepository.deleteCommentLikeByUserIdAndCommentId(commentId, userId);
    }

    public List<UserPreviewResponseDto> getCommentLikes(Long commentId, Optional<Long> lastUserId, int size) {
        return commentLikeRepository.findCommentLikesByCommentId(commentId, lastUserId, size)
                .stream().map(UserInfo::toPreviewDto)
                .toList();
    }
}
