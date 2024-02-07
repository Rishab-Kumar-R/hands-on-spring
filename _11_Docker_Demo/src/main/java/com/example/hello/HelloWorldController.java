package com.example.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/")
    public Response helloWorld() {
        return new Response("Hello, World! This is a Spring Boot application.");
    }
}
