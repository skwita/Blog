package com.skwita.Blog.controller;

import com.skwita.Blog.entity.User;
import com.skwita.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final PostService postService;

    @Autowired
    public HomeController (PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String home(Model model,
                       @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.getAllPosts());
        return "home";
    }

    @PatchMapping("/{id}/like")
    public String like(@PathVariable("id") Long id,
                       @AuthenticationPrincipal User user) {
        postService.changeLikes(id, user);
        return "redirect:/";
    }
}
