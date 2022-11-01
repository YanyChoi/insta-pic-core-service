package com.instantpic.coreservice.repository;

import com.instantpic.coreservice.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        List<UserDto> result = jdbcTemplate.query("SELECT * FROM instapic.user WHERE user_id = ?", userDtoRowMapper(), userId);
        return result.stream().findAny();
    }

    public List<UserDto> readUsers() {
        List<UserDto> result = jdbcTemplate.query("SELECT * FROM instapic.user;", userDtoRowMapper());
        return result;
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
