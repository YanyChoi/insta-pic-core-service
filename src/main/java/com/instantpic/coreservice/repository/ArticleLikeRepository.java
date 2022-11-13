package com.instantpic.coreservice.repository;

import com.instantpic.coreservice.dto.article.ArticleDto;
import com.instantpic.coreservice.dto.like.ArticleLikeDto;
import com.instantpic.coreservice.dto.like.ArticleLikeList;
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
public class ArticleLikeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleLikeRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<ArticleLikeDto> postArticleLike(ArticleLikeDto articleLike)
    {
        jdbcTemplate.update("INSERT INTO instapic.article_like (user_id) VALUES (?);", articleLike.getUserId());
        List<ArticleLikeDto> result = jdbcTemplate.query("SELECT * FROM instapic.article_like WHERE user_id = ? ORDER BY datetime DESC;", articlelikeDtoRowMapper(), articleLike.getUserId());
        return result.stream().findAny();
    }

    public ArticleLikeList getArticleLikeListByArticleId(String articleId)
    {
        ArticleLikeList articlelikeList = new ArticleLikeList();
        articlelikeList.setArticleLikeList(jdbcTemplate.query("SELECT * FROM instapic.article_like WHERE article_id = ?;", articlelikeDtoRowMapper(), articleId));
        return articlelikeList;  //여기 ArticleLikeList의 count도 반환 시켜야 할 거 같은데 어떻게 하는 지 잘 모르겠습니다.`
    }

    //end point로는 user_id와 article_id를 input했을 때, 좋아요를 눌렀는 여부를 반환시켜주는 게 있었는데 어떻게 구현해야 할 지 몰라서 남겨 두었습니다.

    public Optional<ArticleLikeDto> deleteArticleLike(int articleId, String userId)
    {
        List<ArticleLikeDto> result = jdbcTemplate.query("SELECT * FROM instapic.article_like WHERE article_id = ? & user_id = ?;", articlelikeDtoRowMapper(), articleId, userId);
        jdbcTemplate.update("DELETE FROM instapic.article_like WHERE article_id = ? & user_id = ?; ", articleId, userId);
        return result.stream().findAny();
    }

    private RowMapper<ArticleLikeDto> articlelikeDtoRowMapper() {
        return (rs, rowNum) -> {
            ArticleLikeDto articlelike = new ArticleLikeDto();
            articlelike.setArticleId(rs.getInt("article_id"));
            articlelike.setUserId(rs.getString("user_id"));
            return articlelike;
        };
    }
}

