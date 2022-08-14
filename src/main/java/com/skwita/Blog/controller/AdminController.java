package com.skwita.Blog.controller;

import com.skwita.Blog.service.PostService;
import com.skwita.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    UserService userService;
    PostService postService;

    @Autowired
    public AdminController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("posts", postService.findAll());
        return "admin";
    }
}
