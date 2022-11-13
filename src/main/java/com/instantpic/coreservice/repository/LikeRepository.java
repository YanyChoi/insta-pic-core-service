package com.instantpic.coreservice.repository;

import com.instantpic.coreservice.dto.like.ArticleLikeDto;
import com.instantpic.coreservice.dto.like.ArticleLikeList;
import com.instantpic.coreservice.dto.like.CommentLikeDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class LikeRepository {

    private JdbcTemplate jdbcTemplate;

    public LikeRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private RowMapper<ArticleLikeDto> articleLikeDtoRowMapper() {
        return (rs, rowNum) -> {
            ArticleLikeDto like = new ArticleLikeDto();
            like.setArticleId(rs.getInt("article_id"));
            like.setUserId(rs.getString("user_id"));
            return like;
        };
    }

    private RowMapper<CommentLikeDto> commentLikeDtoRowMapper() {
        return (rs, rowNum) -> {
            CommentLikeDto like = new CommentLikeDto();
            like.setCommentId(rs.getInt("comment_id"));
            like.setUserId(rs.getString("user_id"));
            return like;
        };
    }

    public Optional<ArticleLikeDto> postArticleLike(int articleId, String userId) {
        jdbcTemplate.update("INSERT INTO instapic.article_like (article_id, user_id) VALUES (?, ?);", articleId, userId);
        List<ArticleLikeDto> result = jdbcTemplate.query("SELECT * FROM instapic.article_like WHERE article_id = ? AND user_id = ?;", articleLikeDtoRowMapper(), articleId, userId);
        return result.stream().findAny();
    }

    public List<ArticleLikeDto> getArticleLikes(int articleId) {
        List<ArticleLikeDto> result = jdbcTemplate.query("SELECT * FROM instapic.article_like WHERE article_id = ?;", articleLikeDtoRowMapper(), articleId);
        return result;
    }

    public List<ArticleLikeDto> deleteArticleLikeByArticleId(int articleId) {
        List<ArticleLikeDto> result = jdbcTemplate.query("SELECT * FROM instapic.article_like WHERE article_id = ?;", articleLikeDtoRowMapper(), articleId);
        jdbcTemplate.update("DELETE FROM instapic.article_like WHERE article_id = ?;", articleId);
        return result;
    }

    public List<ArticleLikeDto> deleteArticleLikeByUserIdAndArticleId(int articleId, String userId) {
        List<ArticleLikeDto> result = jdbcTemplate.query("SELECT * FROM instapic.article_like WHERE article_id = ? AND user_id = ?;", articleLikeDtoRowMapper(), articleId, userId);
        jdbcTemplate.update("DELETE FROM instapic.article_like WHERE article_id = ? AND user_id = ?;", articleId, userId);
        return result;
    }

    public Optional<CommentLikeDto> postCommentLike(int commentId, String userId) {
        jdbcTemplate.update("INSERT INTO instapic.comment_like (comment_id, user_id) VALUES (?, ?);", commentId, userId);
        List<CommentLikeDto> result = jdbcTemplate.query("SELECT * FROM instapic.comment_like WHERE comment_id = ? AND user_id = ?;", commentLikeDtoRowMapper(), commentId, userId);
        return result.stream().findAny();
    }

    public List<CommentLikeDto> getCommentLikes(int commentId) {
        List<CommentLikeDto> result = jdbcTemplate.query("SELECT * FROM instapic.comment_like WHERE comment_id = ?;", commentLikeDtoRowMapper(), commentId);
        return result;
    }

    public List<CommentLikeDto> deleteCommentLikeByCommentId(int commentId) {
        List<CommentLikeDto> result = jdbcTemplate.query("SELECT * FROM instapic.comment_like WHERE comment_id = ?", commentLikeDtoRowMapper(), commentId);
        jdbcTemplate.update("DELETE FROM instapic.comment_like WHERE comment_id = ?", commentId);
        return result;
    }

    public List<CommentLikeDto> deleteCommentLikeByCommentIdAndUserId(int commentId, String userId) {
        List<CommentLikeDto> result = jdbcTemplate.query("SELECT * FROM instapic.comment_like WHERE comment_id = ? AND user_id = ?;", commentLikeDtoRowMapper(), commentId, userId);
        jdbcTemplate.update("DELETE FROM instapic.comment_like WHERE comment_id = ? AND user_id = ?", commentId, userId);
        return result;
    }

}
