package com.example._04_JPA_and_Hibernate.course.springdatajpa;

import com.example._04_JPA_and_Hibernate.course.Course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {
    List<Course> findByAuthor(String author);
    List<Course> findByName(String name);
}
