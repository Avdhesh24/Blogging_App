package com.blogging_Platform.blogging.repository;

import com.blogging_Platform.blogging.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByBlog_Id(int blogId);
}
