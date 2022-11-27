package com.instantpic.coreservice.repository;

import com.instantpic.coreservice.dto.article.ArticleDto;
import com.instantpic.coreservice.dto.article.ArticleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
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
        List<ArticleDto> result = jdbcTemplate.query("SELECT article.*, media.url AS thumbnail, 0 AS like_count, 0 AS comment_count FROM instapic.article AS article LEFT JOIN instapic.media AS media ON article.article_id = media.article_id WHERE article.user_id = ? ORDER BY article.datetime DESC;", articleDtoRowMapper(), article.getUserId());
        return result.stream().findAny();
    }

    public ArticleList getFeedArticlesByUserId(String feedUserId) {
        ArticleList articleList = new ArticleList();
        articleList.setArticleList(jdbcTemplate.query("SELECT article.*, media.url AS thumbnail, likes.like_count, comments.comment_count FROM instapic.article AS article INNER JOIN instapic.media AS media ON article.article_id = media.article_id LEFT JOIN (SELECT article_id, COUNT(*) as like_count FROM instapic.article_like WHERE article_id IN (SELECT article_id FROM instapic.article WHERE user_id = ?) GROUP BY article_id) AS likes ON likes.article_id = article.article_id LEFT JOIN (SELECT article_id, COUNT(*) as comment_count FROM instapic.comment WHERE article_id IN (SELECT article_id FROM instapic.article WHERE user_id = ?) GROUP BY article_id) AS comments ON comments.article_id = article.article_id WHERE user_id IN (SELECT following_id FROM instapic.follows WHERE user_id = ?) OR user_id = ? GROUP BY article_id ORDER BY article_id DESC;", articleDtoRowMapper(), feedUserId, feedUserId, feedUserId, feedUserId));
        return articleList;
    }

    public ArticleList getArticleListByUserId(String userId) {
        ArticleList articleList = new ArticleList();
        articleList.setArticleList(jdbcTemplate.query("SELECT article.*, media.url AS thumbnail, likes.like_count, comments.comment_count FROM instapic.article AS article INNER JOIN instapic.media AS media ON article.article_id = media.article_id LEFT JOIN (SELECT article_id, COUNT(*) as like_count FROM instapic.article_like WHERE article_id IN (SELECT article_id FROM instapic.article WHERE user_id = ?) GROUP BY article_id) AS likes ON likes.article_id = article.article_id LEFT JOIN (SELECT article_id, COUNT(*) as comment_count FROM instapic.comment WHERE article_id IN (SELECT article_id FROM instapic.article WHERE user_id = ?) GROUP BY article_id) AS comments ON comments.article_id = article.article_id WHERE article.user_id = ? GROUP BY article.article_id;", articleDtoRowMapper(), userId, userId, userId));
        return articleList;
    }

    public ArticleList getArticleListByLocation(String location) {
        ArticleList articleList = new ArticleList();
        articleList.setArticleList(jdbcTemplate.query("SELECT article.*, media.url AS thumbnail, likes.like_count, comments.comment_count FROM instapic.article AS article INNER JOIN instapic.media AS media ON article.article_id = media.article_id LEFT JOIN (SELECT article_id, COUNT(*) as like_count FROM instapic.article_like WHERE article_id IN (SELECT article_id FROM instapic.article WHERE location = ?) GROUP BY article_id) AS likes ON likes.article_id = article.article_id LEFT JOIN (SELECT article_id, COUNT(*) as comment_count FROM instapic.comment WHERE article_id IN (SELECT article_id FROM instapic.article WHERE location = ?) GROUP BY article_id) AS comments ON comments.article_id = article.article_id WHERE article.location = ? GROUP BY article.article_id;", articleDtoRowMapper(), location, location, location));
        return articleList;
    }

    public Optional<ArticleDto> getArticleById(int articleId) {
        List<ArticleDto> result = jdbcTemplate.query("SELECT article.*, media.url AS thumbnail, likes.like_count, comments.comment_count FROM instapic.article AS article INNER JOIN instapic.media AS media ON article.article_id = media.article_id LEFT JOIN (SELECT article_id, COUNT(*) as like_count FROM instapic.article_like WHERE article_id = ?) AS likes ON likes.article_id = article.article_id LEFT JOIN (SELECT article_id, COUNT(*) as comment_count FROM instapic.comment WHERE article_id = ?) AS comments ON comments.article_id = article.article_id WHERE article.article_id = ? GROUP BY article.article_id;", articleDtoRowMapper(), articleId, articleId, articleId);
        return result.stream().findAny();
    }

    @Transactional
    public Optional<ArticleDto> deleteArticle(int articleId) {
        List<ArticleDto> result = jdbcTemplate.query("SELECT article.*, media.url AS thumbnail, likes.like_count, comments.comment_count FROM instapic.article AS article INNER JOIN instapic.media AS media ON article.article_id = media.article_id LEFT JOIN (SELECT article_id, COUNT(*) as like_count FROM instapic.article_like WHERE article_id = ?) AS likes ON likes.article_id = article.article_id LEFT JOIN (SELECT article_id, COUNT(*) as comment_count FROM instapic.comment WHERE article_id = ?) AS comments ON comments.article_id = article.article_id WHERE article.article_id = ? GROUP BY article.article_id;", articleDtoRowMapper(), articleId, articleId, articleId);

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
            article.setThumbnail(rs.getString("thumbnail"));
            article.setLikes(rs.getInt("like_count"));
            article.setComments(rs.getInt("comment_count"));
            return article;
        };
    }
}