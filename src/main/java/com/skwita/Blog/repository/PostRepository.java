package com.skwita.Blog.repository;

import com.skwita.Blog.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
