package com.skwita.Blog.repository;

import com.skwita.Blog.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Comment getCommentById(long id);
}
