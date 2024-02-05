package com.example.aop.data;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class DataService2 {
    public List<String> retrieveData() {
        return Arrays.asList("Spring", "Spring Boot", "Spring AOP", "Spring MVC", "Spring Security");
    }
}
