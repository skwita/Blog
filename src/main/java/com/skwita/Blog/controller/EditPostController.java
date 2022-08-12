package com.skwita.Blog.controller;

import com.skwita.Blog.entity.Post;
import com.skwita.Blog.repository.PostRepository;
import com.skwita.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/edit")
public class EditPostController {
    PostRepository postRepository;
    UserRepository userRepository;

    @Autowired
    public EditPostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public String editPost(@PathVariable("id") long id,
                         Model model) {
        model.addAttribute("post", postRepository.getPostById(id));
        return "editPost";
    }

    @PatchMapping("/{id}")
    public String editPostDone(@ModelAttribute("post") Post post) {
        postRepository.save(post);
        return "redirect:/";
    }
}
