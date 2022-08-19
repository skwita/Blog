package com.skwita.Blog.service;

import com.skwita.Blog.entity.Comment;
import com.skwita.Blog.entity.Post;
import com.skwita.Blog.entity.User;
import com.skwita.Blog.repository.CommentRepository;
import com.skwita.Blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final CommentRepository commentRepository;
    @Autowired
    public PostService(PostRepository postRepository, UserService userService, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.commentRepository = commentRepository;
    }

    public Post getPostById(long id) {
        return postRepository.getPostById(id);
    }
    public void savePost(Post post) {
        postRepository.save(post);
    }
    public Iterable<Post> getAllPosts() {
        return postRepository.findAllByOrderById();
    }
    public void deletePostById(long id) {
        postRepository.deleteById(id);
    }
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }
    public Iterable<Post> findAllByUserId(long id) {
        return postRepository.findAllByUser(userService.findById(id));
    }
    public int changeLikes(long id, User user) {
        Post postToUpdate = postRepository.getPostById(id);
        Set<User> postLikes = postToUpdate.getUserLikes();
        User userThis = userService.findById(user.getId());
        if (!postLikes.contains(userThis)) {
            postLikes.add(userThis);
        } else {
            postLikes.remove(userThis);
        }
        postToUpdate.setUserLikes(postLikes);
        postRepository.save(postToUpdate);
        return postToUpdate.getUserLikes().size();
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.getCommentById(id);
    }
}
