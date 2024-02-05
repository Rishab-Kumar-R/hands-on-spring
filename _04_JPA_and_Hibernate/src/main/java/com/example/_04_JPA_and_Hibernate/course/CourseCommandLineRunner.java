package com.example._04_JPA_and_Hibernate.course;

import com.example._04_JPA_and_Hibernate.course.springdatajpa.CourseSpringDataJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {
//    @Autowired
//    private CourseJdbcRepository repository;

//    @Autowired
//    private CourseJpaRepository repository;

    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Course(1, "Python", "Eric"));
        repository.save(new Course(2, "Java Software Development", "John"));
        repository.save(new Course(3, "C++", "Tom"));

        repository.deleteById(1L);

        System.out.println(repository.findById(2L));
        System.out.println(repository.findById(3L));

        repository.findAll().forEach(System.out::println);
        System.out.println("Number of courses: " + repository.count());

        System.out.println(repository.findByAuthor("John"));
        System.out.println(repository.findByName("C++"));
    }
}
