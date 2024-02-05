package com.example._04_JPA_and_Hibernate.course.jpa;

import com.example._04_JPA_and_Hibernate.course.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJpaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void insert(Course course) {
        entityManager.merge(course);
    }

    public void deleteById(long id) {
        entityManager.remove(findById(id));
    }

    public Course findById(long id) {
        return entityManager.find(Course.class, id);
    }

}
