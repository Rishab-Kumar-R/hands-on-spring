package com.rishab.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting() {
        return "greeting-form";
    }

    @GetMapping("/process-greeting")
    public String processGreeting() {
        return "greeting";
    }

    @GetMapping("/process-greeting-v2")
    public String processGreetingV2(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        name = name.toUpperCase();
        String greeting = "Yello, " + name + "!";
        model.addAttribute("greeting", greeting);
        return "greeting";
    }

    @PostMapping("/process-greeting-v3")
    public String processGreetingV3(@RequestParam("name") String name, Model model) {
        name = name.toUpperCase();
        String greeting = "Hey, " + name + " what's up?";
        model.addAttribute("greeting", greeting);
        return "greeting";
    }

}
