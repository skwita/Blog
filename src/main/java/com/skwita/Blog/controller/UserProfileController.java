package com.skwita.Blog.controller;

import com.skwita.Blog.entity.User;
import com.skwita.Blog.service.PostService;
import com.skwita.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserProfileController {
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public UserProfileController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id,
                           Model model,
                           @AuthenticationPrincipal User user) {
        model.addAttribute("author", userService.findById(id));
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.findAllByUserId(id));
        return "user";
    }
}
