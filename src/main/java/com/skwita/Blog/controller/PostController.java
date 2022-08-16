package com.skwita.Blog.controller;

import com.skwita.Blog.entity.Comment;
import com.skwita.Blog.entity.User;
import com.skwita.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

//    @PatchMapping("/{id}/like")
//    public String like(@PathVariable("id") Long id,
//                       @AuthenticationPrincipal User user) {
//        postService.changeLikes(id, user);
//        return "redirect:/";
//    }

    @MessageMapping("/{id}/like")
    public Mono<Integer> handleLike(Mono<Integer> likeMono,
                                    @AuthenticationPrincipal User user) {
        return likeMono.doOnNext(like ->
              postService.changeLikes(like, user)
        ).map(like -> postService.getPostById(like).getUserLikes().size());
    }

    @PostMapping("/{id}/comment")
    public String comment(@PathVariable("id") Long id,
                          @AuthenticationPrincipal User user,
                          @ModelAttribute("comment") @Valid Comment comment,
                          Errors errors,
                          Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("posts", postService.getAllPosts());
            return "home";
        }
        comment.setUser(user);
        comment.setPost(postService.getPostById(id));
        postService.saveComment(comment);
        return "redirect:/";
    }

    @PreAuthorize("@postService.getCommentById(#id).getUser().getUsername() == authentication.name  || " +
            "hasRole('ROLE_ADMIN') || @postService.getPostById(#postId).getUser().getUsername() == authentication.name")
    @DeleteMapping("/{postId}/comment/delete/{id}")
    public String deleteComment(@PathVariable("id") Long id,
                                @PathVariable String postId) {
        postService.deleteCommentById(id);
        return "redirect:/";
    }
}
