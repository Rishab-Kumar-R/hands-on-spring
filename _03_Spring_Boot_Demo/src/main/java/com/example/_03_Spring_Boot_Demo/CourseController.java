package com.example._03_Spring_Boot_Demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {
    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses() {
        return Arrays.asList(
            new Course(1, "Spring Boot", "Miguel"),
            new Course(2, "Flutter", "Zack"),
            new Course(3, "React", "Max"),
            new Course(4, "Angular", "John")
        );
    }
}
