package com.blogging_Platform.blogging.repository;

import com.blogging_Platform.blogging.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

    List<Like> findByBlog_Id(int blogId);

    List<Like> findByUser_IdAndBlog_Id(int userId, int blogId);
}
