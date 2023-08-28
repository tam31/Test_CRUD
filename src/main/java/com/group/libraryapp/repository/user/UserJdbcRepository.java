package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.request.user.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(String name, int age){
        String sql = "INSERT INTO user(name, age) VALUES(?, ?)";
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse> getUserResponses(){
        String sql = "select * from user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id,name,age);
        });
    }

    public void updateUserName(String name, long id){
        String sql = "update user set name = ? where id = ?";
        jdbcTemplate.update(sql, name,id);
    }

    public void deleteUserByName(String name){
        String sql = "delete from user where name = ?";
        jdbcTemplate.update(sql, name);
    }

    public boolean isUserNotExist(long id){
        String readsql = "select * from user where id = ?";
        return jdbcTemplate.query(readsql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public boolean isUserNotExist(String name){
        String readsql = "select * from user where name = ?";
        return jdbcTemplate.query(readsql, (rs, rowNum) -> 0, name).isEmpty();
    }
}
