package com.skwita.Blog.controller;

import com.skwita.Blog.entity.Post;
import com.skwita.Blog.repository.PostRepository;
import com.skwita.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    PostRepository postRepository;
    UserRepository userRepository;

    @Autowired
    public NewPostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String newPost(@ModelAttribute("post") Post post) {
        return "createPost";
    }

    @PostMapping
    public String newPostDone(@ModelAttribute("post") @Valid Post post, Errors errors){
        if (errors.hasErrors()){
            return "createPost";
        }
        post.setUser(userRepository.findByUsername("skwita"));
        postRepository.save(post);
        return "redirect:/";
    }
}
