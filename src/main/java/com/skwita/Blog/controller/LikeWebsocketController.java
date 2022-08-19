package com.skwita.Blog.controller;

import com.skwita.Blog.entity.User;
import com.skwita.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

//@Controller
public class LikeWebsocketController {
    private final PostService postService;

    @Autowired
    public LikeWebsocketController(PostService postService) {
        this.postService = postService;
    }

    @MessageMapping("/post/{id}/like")
    @SendTo("/")
    public Integer like(@AuthenticationPrincipal UserDetails userDetails,
                        @PathVariable Long id) {
        return postService.changeLikes(id, (User) userDetails);
    }
}