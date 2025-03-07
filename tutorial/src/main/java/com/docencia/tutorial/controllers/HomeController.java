package com.docencia.tutorial.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Welcome to Spring Boot");
        model.addAttribute("subtitle", "An Spring Boot Eafit App");
        return "home/index";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Us - Online Store");
        model.addAttribute("subtitle", "About Us");
        model.addAttribute("description", "This is an about page ...");
        model.addAttribute("author", "Developed by: Jose M Burgos");
        return "home/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact - Online Store");
        model.addAttribute("subtitle", "Contact");
        model.addAttribute("email", "contact@example.com");
        model.addAttribute("address", "1234 Carabobo, Medellín, Colombia");
        model.addAttribute("phone", "+57 300 123 4567");
        return "home/contact";
    }

}
