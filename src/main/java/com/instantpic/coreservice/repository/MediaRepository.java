package com.instantpic.coreservice.repository;

import com.instantpic.coreservice.dto.media.MediaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.Media;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static java.sql.Types.NULL;

@Repository
public class MediaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MediaRepository(DataSource dataSource) { jdbcTemplate = new JdbcTemplate(dataSource); }
    @Transactional
    public boolean uploadMedia(List<String> mediaUrls, int articleId){
        try {
            for (String url: mediaUrls) {
                jdbcTemplate.update(
                        "INSERT INTO instapic.media (url, article_id) VALUES (?, ?);",
                        url, articleId
                );
            }
            return true;

        }catch (Exception e){
            return false;
        }
    }
    public List<MediaDto> readMediaByArticleId(int articleId){
        List<MediaDto> result = jdbcTemplate.query("SELECT * FROM instapic.media WHERE article_id = ?", mediaDtoRowMapper(), articleId);
        return result;
    }

    @Transactional
    public List<MediaDto> deleteSeparateMedia(int mediaId){
        List<MediaDto> result = jdbcTemplate.query("SELECT * FROM instapic.media WHERE media_id = ?", mediaDtoRowMapper(), mediaId);
        jdbcTemplate.update("DELETE FROM instapic.media WHERE media_id = ?", mediaId);
        return result;
    }

    @Transactional
    public List<MediaDto> deleteAllMedia(int articleId){
        List<MediaDto> result = jdbcTemplate.query("SELECT * FROM instapic.media WHERE article_id = ?", mediaDtoRowMapper(), articleId);
        jdbcTemplate.update("DELETE FROM instapic.media WHERE article_id = ?", articleId);
        return result;
    }

    private RowMapper<MediaDto> mediaDtoRowMapper(){
        return (rs, rowNum) -> {
            MediaDto media = new MediaDto();
            media.setMediaId(rs.getInt("media_id"));
            media.setUrl(rs.getString("url"));
            media.setArticleId(rs.getInt("article_id"));

            return media;
        };
    }
}