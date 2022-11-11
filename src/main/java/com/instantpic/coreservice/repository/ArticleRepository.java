package com.instantpic.coreservice.repository;

import com.instantpic.coreservice.dto.article.ArticleDto;
import com.instantpic.coreservice.dto.article.ArticleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    public Optional<ArticleDto> postArticle(ArticleDto article) {
        jdbcTemplate.update("INSERT INTO instapic.article (location, text, user_id) VALUES (?, ?, ?);",
                article.getLocation(), article.getText(), article.getUserId());
        List<ArticleDto> result = jdbcTemplate.query("SELECT * FROM instapic.article WHERE user_id = ? ORDER BY datetime DESC;", articleDtoRowMapper(), article.getUserId());
        return result.stream().findAny();
    }

    public ArticleList getArticleListByUserId(String userId) {
        ArticleList articleList = new ArticleList();
        articleList.setArticleList(jdbcTemplate.query("SELECT * FROM instapic.article WHERE user_id = ?;", articleDtoRowMapper(), userId));
        return articleList;
    }

    public ArticleList getArticleListByLocation(String location) {
        ArticleList articleList = new ArticleList();
        articleList.setArticleList(jdbcTemplate.query("SELECT * FROM instapic.article WHERE location = ?;", articleDtoRowMapper(), location));
        return articleList;
    }

    public Optional<ArticleDto> getArticleById(int articleId) {
        List<ArticleDto> result = jdbcTemplate.query("SELECT * FROM instapic.article WHERE article_id = ?;", articleDtoRowMapper(), articleId);
        return result.stream().findAny();
    }

    @Transactional
    public Optional<ArticleDto> deleteArticle(int articleId) {
        List<ArticleDto> result = jdbcTemplate.query("SELECT * FROM instapic.article WHERE article_id = ?;", articleDtoRowMapper(), articleId);
        jdbcTemplate.update("DELETE FROM instapic.article WHERE article_id = ?", articleId);
        return result.stream().findAny();
    }
    private RowMapper<ArticleDto> articleDtoRowMapper() {
        return (rs, rowNum) -> {
            ArticleDto article = new ArticleDto();
            article.setArticleId(rs.getInt("article_id"));
            article.setDate(rs.getString("datetime"));
            article.setLocation(rs.getString("location"));
            article.setText(rs.getString("text"));
            article.setUserId(rs.getString("user_id"));
            return article;
        };
    }
}