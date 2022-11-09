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

@Repository
public class MediaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MediaRepository(DataSource dataSource) { jdbcTemplate = new JdbcTemplate(dataSource); }

    public Optional<MediaDto> readMediaByArticleId(int articleId){
        List<MediaDto> result = jdbcTemplate.query("SELECT * FROM instapic.media WHERE article_id = ?", mediaDtoRowMapper(), articleId);
        return result.stream().findAny();
    }

    public Optional<MediaDto> deleteMedia(int articleId, int mediaId, String url){
        List<MediaDto> result = jdbcTemplate.query("DELETE * FROM instapic.media (media_id, url) WHERE article_id = ? AND media_id = ? AND url =?", mediaDtoRowMapper(), articleId, mediaId, url);
        return result.stream().findAny();
    }

    @Transactional
    public boolean uploadMedia(MediaDto media){
        try{
            jdbcTemplate.update(
                    "INSERT INTO instapic.media (url) VALUES (?);",
                    media.getUrl()
            );
            return true;
        }catch (Exception e){
            return false;
        }
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