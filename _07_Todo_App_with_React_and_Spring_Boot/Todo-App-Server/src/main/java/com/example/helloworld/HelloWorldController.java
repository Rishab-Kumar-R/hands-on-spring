package com.example.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!!!";
    }

    @GetMapping("/hello-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World!");
    }

    // Path Parameter
    @GetMapping("/hello-bean/path-variable/{name}")
    public HelloWorldBean helloPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello %s", name));
    }

    @GetMapping("/hello-internationalized")
    public String helloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    }

    @GetMapping("/basic-auth")
    public String basicAuth() {
        return "You are authenticated";
    }
}
