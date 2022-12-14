package com.skwita.Blog.repository;

import com.skwita.Blog.entity.Post;
import com.skwita.Blog.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Post getPostById(long id);
    Iterable<Post> findAllByOrderById();
    Iterable<Post> findAllByUser(User user);
}
