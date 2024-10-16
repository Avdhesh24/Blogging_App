package com.blogging_Platform.blogging.serviceImpl;

import com.blogging_Platform.blogging.models.Blog;
import com.blogging_Platform.blogging.models.Category;
import com.blogging_Platform.blogging.models.Tag;
import com.blogging_Platform.blogging.models.User;
import com.blogging_Platform.blogging.repository.BlogRepository;
import com.blogging_Platform.blogging.repository.CategoryRepository;
import com.blogging_Platform.blogging.repository.TagRepository;
import com.blogging_Platform.blogging.repository.UserRepository;
import com.blogging_Platform.blogging.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    // Create a new blog
    @Override
    public Blog createBlog(Blog blog, int userId, int categoryId) {
        // Check if user and category exist
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        blog.setUser(user);
        blog.setCategory(category);
        return blogRepository.save(blog);
    }

    // Get a blog by its ID
    @Override
    public Blog getBlogById(int blogId) {
        return blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    // Get all blogs
    @Override
    public List<Blog> allBlogsGet() {
        return blogRepository.findAll();
    }

    // Update blog by ID
    @Override
    public Blog updateBlog(int blogId, Blog updatedBlog) {
        return blogRepository.findById(blogId).map(existingBlog -> {
            existingBlog.setTitle(updatedBlog.getTitle());
            existingBlog.setContent(updatedBlog.getContent());
            existingBlog.setTags(updatedBlog.getTags());
            return blogRepository.save(existingBlog);
        }).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    // Delete a blog by its ID
    @Override
    public boolean deleteBlog(int blogId) {
        return blogRepository.findById(blogId).map(blog -> {
            blogRepository.delete(blog);
            return true;
        }).orElse(false);
    }

    // Get blogs by category ID
    @Override
    public List<Blog> getBlogsByCategory(int categoryId) {
        return blogRepository.findByCategory_Id(categoryId);
    }

    // Get blogs by tag ID
    @Override
    public List<Blog> getBlogsByTag(int tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        return tag.getBlogs();
    }

    @Override
    public List<Blog> getBlogsByUser(int userId) {
        return List.of();
    }


}
