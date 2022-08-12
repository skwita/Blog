package com.skwita.Blog.controller;

import com.skwita.Blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete")
public class DeletePostController {
    PostRepository postRepository;

    @Autowired
    public DeletePostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") long id) {
        postRepository.deleteById(id);
        return "redirect:/";
    }
}
