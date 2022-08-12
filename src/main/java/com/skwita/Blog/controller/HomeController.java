package com.skwita.Blog.controller;

import com.skwita.Blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    PostRepository postRepository;

    @Autowired
    public HomeController (PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("posts", postRepository.findAllByOrderById());
        return "home";
    }
}
