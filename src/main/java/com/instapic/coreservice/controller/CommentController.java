package com.instapic.coreservice.controller;

import com.instapic.coreservice.dto.comment.CommentDto;
import com.instapic.coreservice.dto.comment.CommentList;
import com.instapic.coreservice.dto.request.comment.CommentPostRequestDto;
import com.instapic.coreservice.dto.response.comment.CommentResponseDto;
import com.instapic.coreservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/article/{articleId}/comment")
    public ResponseEntity<Void> postComment(@PathVariable Long articleId, @RequestBody CommentPostRequestDto comment) {
        commentService.createComment(comment, articleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/article/{articleId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long articleId, @RequestParam Long lastCommentId, @RequestParam int size) {
        List<CommentResponseDto> result = commentService.getCommentsByArticle(articleId, lastCommentId, size);
        return ResponseEntity.ok().body(result);
    }
}
