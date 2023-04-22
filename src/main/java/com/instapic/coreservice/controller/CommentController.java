package com.instapic.coreservice.controller;

import com.instapic.coreservice.dto.request.comment.CommentPostRequestDto;
import com.instapic.coreservice.dto.response.comment.CommentResponseDto;
import com.instapic.coreservice.dto.response.user.UserPreviewResponseDto;
import com.instapic.coreservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/article/{articleId}/comment")
    public ResponseEntity<Void> postComment(@PathVariable Long articleId, @RequestBody CommentPostRequestDto comment) {
        List<String> mentions = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<=@)\\S+(?=\\s)");
        Matcher matcher = pattern.matcher(comment.getText());
        while (matcher.find()) {
            log.info(matcher.group());
            mentions.add(matcher.group());
        }
        commentService.createComment(comment, articleId, mentions);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/article/{articleId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long articleId, @RequestParam(required = false) Optional<Long> lastCommentId, @RequestParam int size) {
        List<CommentResponseDto> result = commentService.getCommentsByArticle(articleId, lastCommentId, size);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/comment/{commentId}/likes")
    public ResponseEntity<List<UserPreviewResponseDto>> getCommentLikeUsers(@PathVariable Long commentId, @RequestParam(required = false) Optional<Long> lastUserId, @RequestParam int size) {
        return ResponseEntity.ok().body(commentService.getCommentLikes(commentId, lastUserId, size));
    }

    @PostMapping("/comment/{commentId}/like")
    public ResponseEntity<Void> postCommentLike(@PathVariable Long commentId, @RequestParam Long userId) {
        commentService.createCommentLike(commentId, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/comment/{commentId}/like")
    public ResponseEntity<Void> deleteCommentLike(@PathVariable Long commentId, @RequestParam Long userId) {
        commentService.deleteCommentLike(commentId, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
