package com.example._05_Todo_App.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello! What are we learning today? Spring Boot!";
    }

    @RequestMapping("/hello-html")
    @ResponseBody
    public String sayHelloWithHTML() {
        return "<h1>Hello! What are we learning today? Spring Boot!</h1>";
    }

    @RequestMapping("/hello-jsp")
    public String sayHelloWithJSP() {
        return "sayHello";
    }
}
