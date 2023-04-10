package com.instapic.coreservice.repository;

import com.instapic.coreservice.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<UserDto> readUserByIdAndPw(String userId, String pw) {
        List<UserDto> result = jdbcTemplate.query("SELECT * FROM instapic.user WHERE user_id = ? AND pw = ?", userDtoRowMapper(), userId, pw);
        return result.stream().findAny();
    }

    public Optional<UserDto> readUserById(String userId) {
        List<UserDto> result = jdbcTemplate.query("SELECT user_id, NULL AS pw ,name, profile_pic, introduction, url FROM instapic.user WHERE user_id = ?", userDtoRowMapper(), userId);
        return result.stream().findAny();
    }

    public List<UserDto> readUserByKeyword(String keyword) {
        String condition = '%' + keyword + '%';
        List<UserDto> result = jdbcTemplate.query("SELECT * FROM instapic.user WHERE user_id LIKE ?;", userDtoRowMapper(), condition);
        return result;
    }

    @Transactional
    public Optional<UserDto> updateUserPw(String id, String pw) {
        jdbcTemplate.update("UPDATE instapic.user SET pw = ? WHERE user_id = ?", pw, id);
        List<UserDto> result = jdbcTemplate.query("SELECT * FROM instapic.user WHERE user_id = ? AND pw = ?", userDtoRowMapper(), id, pw);
        return result.stream().findAny();
    }

    @Transactional
    public boolean createUser(UserDto user) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO instapic.user (user_id, pw, name, profile_pic, introduction, url) VALUES (?, ?, ?, ?, ?, ?);",
                    user.getUserId(), user.getPw(), user.getName(), user.getProfilePic(), user.getIntroduction(), user.getUrl()
            );
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }



    private RowMapper<UserDto> userDtoRowMapper() {
        return (rs, rowNum) -> {
            UserDto user = new UserDto();
            user.setUserId(rs.getString("user_id"));
            user.setName(rs.getString("name"));
            user.setIntroduction(rs.getString("introduction"));
            user.setUrl(rs.getString("url"));
            user.setPw(rs.getString("pw"));
            user.setProfilePic(rs.getString("profile_pic"));

            return user;
        };
    }
}
