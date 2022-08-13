package com.skwita.Blog.controller;

import com.skwita.Blog.entity.Post;
import com.skwita.Blog.entity.User;
import com.skwita.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/edit")
public class EditPostController {
    PostService postService;

    @Autowired
    public EditPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public String editPost(@PathVariable("id") long id,
                           Model model,
                           @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        model.addAttribute("post", postService.getPostById(id));
        return "editPost";
    }

    @PatchMapping("/{id}")
    public String editPostDone(@ModelAttribute("post") @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return "editPost";
        }
        postService.savePost(post);
        return "redirect:/";
    }
}
