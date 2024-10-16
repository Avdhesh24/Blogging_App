package com.blogging_Platform.blogging.service;

import com.blogging_Platform.blogging.models.Blog;

import java.util.List;


public interface BlogService {

    Blog createBlog(Blog blog, int userId, int categoryId);

    Blog getBlogById(int blogId);

    List<Blog> allBlogsGet();

    Blog updateBlog(int blogId, Blog updatedBlog);

    boolean deleteBlog(int blogId);

    List<Blog> getBlogsByCategory(int categoryId);

    List<Blog> getBlogsByTag(int tagId);

    List<Blog> getBlogsByUser(int userId);
}
