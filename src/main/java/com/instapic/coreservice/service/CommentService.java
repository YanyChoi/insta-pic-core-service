package com.instapic.coreservice.service;

import com.instapic.coreservice.domain.Article;
import com.instapic.coreservice.domain.Comment;
import com.instapic.coreservice.domain.User;
import com.instapic.coreservice.dto.request.comment.CommentPostRequestDto;
import com.instapic.coreservice.dto.response.comment.CommentResponseDto;
import com.instapic.coreservice.repository.ArticleRepository;
import com.instapic.coreservice.repository.UserRepository;
import com.instapic.coreservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    @Transactional
    public void createComment(CommentPostRequestDto dto, Long articleId) throws NoSuchElementException {
        User author = userRepository.findById(dto.getUserId()).orElseThrow(() -> new NoSuchElementException("No such user with ID " + dto.getUserId()));
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new NoSuchElementException("No such article with ID " + articleId));
        Comment comment = Comment.builder()
                .article(article)
                .author(author)
                .text(dto.getText())
                .build();
        commentRepository.save(comment);
    }

    public List<CommentResponseDto> getCommentsByArticle(Long articleId, Long lastCommentId, int size) {
        return commentRepository.findCommentsWithArticleId(articleId, lastCommentId, size)
                .stream().map(Comment::toDto)
                .toList();
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
