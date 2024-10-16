package com.blogging_Platform.blogging.repository;

import com.blogging_Platform.blogging.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByUser_Id(Integer userId);  // Corrected field name

    List<Blog> findByCategory_Id(Integer categoryId);
}
