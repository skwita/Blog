package com.skwita.Blog.controller;

import com.skwita.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete")
public class DeletePostController {
    UserService userService;

    @Autowired
    public DeletePostController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }
}
