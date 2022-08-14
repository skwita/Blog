package com.skwita.Blog.service;

import com.skwita.Blog.entity.Post;
import com.skwita.Blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
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
}
