package com.rishab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/managers")
    public String managers() {
        return "managers";
    }

    @GetMapping("/admins")
    public String admins() {
        return "admins";
    }
}
