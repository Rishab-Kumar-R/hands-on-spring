package com.example._04_JPA_and_Hibernate.course.jdbc;

import com.example._04_JPA_and_Hibernate.course.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final String INSERT_SQL = "INSERT INTO course (id, name, author) VALUES (?, ?, ?)";
    public static final String DELETE_SQL = "DELETE FROM course WHERE id=?";
    public static final String SELECT_SQL = "SELECT * FROM course WHERE id=?";

    public void insert(Course course) {
        jdbcTemplate.update(INSERT_SQL, course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteById(long id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

    public Course findById(long id) {
        return jdbcTemplate.queryForObject(SELECT_SQL, new BeanPropertyRowMapper<>(Course.class), id);
    }
}
