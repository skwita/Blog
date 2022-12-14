package com.skwita.Blog.controller;

import com.skwita.Blog.entity.Post;
import com.skwita.Blog.entity.User;
import com.skwita.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/new")
public class NewPostController {
    private final PostService postService;

    @Autowired
    public NewPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String newPost(@ModelAttribute("post") Post postModel, Model model,
                          @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        return "createPost";
    }

    @PostMapping
    public String newPostDone(@ModelAttribute("post") @Valid Post post,
                              Errors errors,
                              @AuthenticationPrincipal User user){
        if (errors.hasErrors()){
            return "createPost";
        }
        post.setUser(user);
        postService.savePost(post);
        return "redirect:/";
    }
}
