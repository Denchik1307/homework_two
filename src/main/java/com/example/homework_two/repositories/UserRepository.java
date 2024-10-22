package com.example.homework_two.repositories;

import com.example.homework_two.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";
        RowMapper<User> userRowMapper = (r, _) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public void save(User user) {
        String sql = "INSERT INTO userTable VALUES (NULL, ?, ?)";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public User findUserById(Integer id) {
        String sql = "SELECT * FROM userTable WHERE id=?";
        RowMapper<User> userRowMapper = (r, _) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        // Используем queryForObject для получения одного результата
        return jdbcTemplate.queryForObject(sql, userRowMapper, id);
    }

    public void update(User user) {
        String sql = "UPDATE userTable SET firstName=?, lastName=? WHERE id=?";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getId());
    }

}