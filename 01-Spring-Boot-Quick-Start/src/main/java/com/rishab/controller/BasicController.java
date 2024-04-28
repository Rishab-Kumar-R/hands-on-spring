package com.rishab.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    @Value("${rest.api.url}")
    private String restApiUrl;

    @Value("${rest.api.username}")
    private String restApiUsername;

    @Value("${rest.api.password}")
    private String restApiPassword;

    @GetMapping("/")
    public String hello() {
        return "Hello There!";
    }

    @GetMapping("/bye")
    public String bye() {
        return "Goodbye!";
    }

    @GetMapping("/test")
    public String test() {
        return "Testing!";
    }

    @GetMapping("/restapi")
    public String restApi() {
        return "Rest API URL: " + restApiUrl + ", Username: " + restApiUsername + ", Password: " + restApiPassword;
    }
}
