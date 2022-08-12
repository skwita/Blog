package com.skwita.Blog.controller;

import com.skwita.Blog.entity.User;
import com.skwita.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute("user") User user) {
        System.out.println(user);
        userService.registerDefaultUser(user);
        return "login";
    }

}
