package com.skwita.Blog.controller;

import com.skwita.Blog.entity.Post;
import com.skwita.Blog.entity.User;
import com.skwita.Blog.service.PostService;
import com.skwita.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/new")
public class NewPostController {
    PostService postService;
    UserService userService;

    @Autowired
    public NewPostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public String newPost(@ModelAttribute("post") Post post) {
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
