package com.instantpic.coreservice.repository;

import com.instantpic.coreservice.dto.like.ArticleLikeDto;
import com.instantpic.coreservice.dto.like.CommentLikeDto;
import com.instantpic.coreservice.dto.like.CommentLikeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentLikeRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentLikeRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<CommentLikeDto> postCommentLike(CommentLikeDto commentLike)
    {
        jdbcTemplate.update("INSERT INTO instapic.comment_like (user_id) VALUES (?);", commentLike.getUserId());
        List<CommentLikeDto> result = jdbcTemplate.query("SELECT * FROM instapic.comment_like WHERE user_id = ? ORDER BY datetime DESC;", commentlikeDtoRowMapper(), commentLike.getUserId());
        return result.stream().findAny();
    }

    public CommentLikeList getCommentLikeListByCommentId(String commentId)
    {
        CommentLikeList commentlikeList = new CommentLikeList();
        commentlikeList.setCommentLikeList(jdbcTemplate.query("SELECT * FROM instapic.Comment_like WHERE comment_id = ?;", commentlikeDtoRowMapper(), commentId));
        return commentlikeList;  //여기 CommentLikeList의 count도 반환 시켜야 할 거 같은데 어떻게 하는 지 잘 모르겠습니다.`
    }

    public Optional<CommentLikeDto> deleteCommentLike(int commentId, String userId)
    {
        List<CommentLikeDto> result = jdbcTemplate.query("SELECT * FROM instapic.comment_like WHERE comment_id = ? & user_id = ?;", commentlikeDtoRowMapper(), commentId, userId);
        jdbcTemplate.update("DELETE FROM instapic.comment_like WHERE comment_id = ? & user_id = ?; ", commentId, userId);
        return result.stream().findAny();
    }

    private RowMapper<CommentLikeDto> commentlikeDtoRowMapper() {
        return (rs, rowNum) -> {
            CommentLikeDto commentlike = new CommentLikeDto();
            commentlike.setCommentId(rs.getInt("comment_id"));
            commentlike.setUserId(rs.getString("user_id"));
            return commentlike;
        };
    }
}