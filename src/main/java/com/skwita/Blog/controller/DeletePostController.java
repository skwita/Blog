package com.skwita.Blog.controller;

import com.skwita.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete")
public class DeletePostController {
    private final PostService postService;

    @Autowired
    public DeletePostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("@postService.getPostById(#id).getUser().getUsername() == authentication.name")
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") long id) {
        postService.deletePostById(id);
        return "redirect:/";
    }
}
