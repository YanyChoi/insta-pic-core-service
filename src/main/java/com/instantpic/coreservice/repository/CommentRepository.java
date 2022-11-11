package com.instantpic.coreservice.repository;

import com.instantpic.coreservice.dto.comment.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public class CommentRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    public Optional<CommentDto> postComment(CommentDto comment) {
        jdbcTemplate.update("INSERT INTO instapic.comment (article_id, user_id, text, parent_comment_id) VALUES (?, ?, ?, ?);",
                comment.getArticleId(), comment.getUserId(), comment.getText(), comment.getParentCommentId());
        List<CommentDto> result = jdbcTemplate.query("SELECT * FROM instapic.comment WHERE article_id = ? AND user_id = ? ORDER BY comment_id DESC;", commentDtoRowMapper(), comment.getArticleId(), comment.getUserId());
        return result.stream().findAny();
    }

    @Transactional
    public Optional<CommentDto> postCommentMention(int commentId, String mentionedId) {
        jdbcTemplate.update("INSERT INTO instapic.comment_mention (comment_id, user_id) VALUES (?, ?);", commentId, mentionedId);
        List<CommentDto> result = jdbcTemplate.query("SELECT * FROM instapic.comment_mention WHERE comment_id = ? AND user_id = ?", commentMentionMapper(), commentId, mentionedId);
        return result.stream().findAny();
    }

    @Transactional
    public Optional<CommentDto> deleteCommentById(int commentId) {
        List<CommentDto> result = jdbcTemplate.query("SELECT * FROM instapic.comment WHERE comment_id = ?;", commentDtoRowMapper(), commentId);
        jdbcTemplate.update("DELETE FROM instapic.comment WHERE comment_id = ?", commentId);
        return result.stream().findAny();
    }

    public List<CommentDto> getRootCommentsByArticleId(int articleId) {
        List<CommentDto> comments = jdbcTemplate.query("SELECT * FROM instapic.comment WHERE article_id = ? AND parent_comment_id IS NULL;", commentDtoRowMapper(), articleId);
        return comments;
    }

    public List<CommentDto> getChildCommentsByCommentId(int commentId) {
        List<CommentDto> comments = jdbcTemplate.query("SELECT * FROM instapic.comment WHERE parent_comment_id = ?;", commentDtoRowMapper(), commentId);
        return comments;
    }

    private RowMapper<CommentDto> commentDtoRowMapper() {
        return (rs, rowNum) -> {
            CommentDto comment = new CommentDto();
            comment.setCommentId(rs.getInt("comment_id"));
            comment.setArticleId(rs.getInt("article_id"));
            comment.setUserId(rs.getString("user_id"));
            comment.setText(rs.getString("text"));
            comment.setDatetime(rs.getString("datetime"));
            comment.setParentCommentId(rs.getInt("parent_comment_id"));
            return comment;
        };
    }

    private RowMapper<CommentDto> commentMentionMapper() {
        return (rs, rowNum) -> {
            CommentDto comment = new CommentDto();
            comment.setCommentId(rs.getInt("comment_id"));
            comment.setMentionedId(rs.getString("user_id"));
            return comment;
        };
    }
}
