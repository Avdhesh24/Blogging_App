package com.blogging_Platform.blogging.Controllers;

import com.blogging_Platform.blogging.models.Blog;
import com.blogging_Platform.blogging.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // Create a new blog
    @Operation(summary = "Create a new blog", description = "Creates a new blog post, requires user ID and category ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Blog created successfully"),
            @ApiResponse(responseCode = "404", description = "User or Category not found")
    })
    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog,
                                           @RequestParam int userId,
                                           @RequestParam int categoryId) {
        Blog createdBlog = blogService.createBlog(blog, userId, categoryId);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    // Get a blog by ID
    @Operation(summary = "Get a blog by ID", description = "Retrieves a single blog post using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Blog not found")
    })
    @GetMapping("/{blogId}")
    public ResponseEntity<Blog> getBlogById(@PathVariable int blogId) {
        Blog blog = blogService.getBlogById(blogId);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    // Get all blogs
    @Operation(summary = "Get all blogs", description = "Retrieves all the blogs in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blogs retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.allBlogsGet();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    // Update a blog by ID
    @Operation(summary = "Update a blog by ID", description = "Updates an existing blog post using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog updated successfully"),
            @ApiResponse(responseCode = "404", description = "Blog not found")
    })
    @PutMapping("/{blogId}")
    public ResponseEntity<Blog> updateBlog(@PathVariable int blogId,
                                           @RequestBody Blog updatedBlog) {
        Blog blog = blogService.updateBlog(blogId, updatedBlog);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    // Delete a blog by ID
    @Operation(summary = "Delete a blog by ID", description = "Deletes a blog post using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Blog not found")
    })
    @DeleteMapping("/{blogId}")
    public ResponseEntity<String> deleteBlog(@PathVariable int blogId) {
        boolean deleted = blogService.deleteBlog(blogId);
        if (deleted) {
            return new ResponseEntity<>("Blog deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Blog not found", HttpStatus.NOT_FOUND);
        }
    }

    // Get blogs by category ID
    @Operation(summary = "Get blogs by category ID", description = "Retrieves all blogs under a specific category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blogs retrieved successfully")
    })
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<List<Blog>> getBlogsByCategory(@PathVariable int categoryId) {
        List<Blog> blogs = blogService.getBlogsByCategory(categoryId);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    // Get blogs by tag ID
    @Operation(summary = "Get blogs by tag ID", description = "Retrieves all blogs associated with a specific tag.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blogs retrieved successfully")
    })
    @GetMapping("/tags/{tagId}")
    public ResponseEntity<List<Blog>> getBlogsByTag(@PathVariable int tagId) {
        List<Blog> blogs = blogService.getBlogsByTag(tagId);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    // Get blogs by user ID
    @Operation(summary = "Get blogs by user ID", description = "Retrieves all blogs created by a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blogs retrieved successfully")
    })
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Blog>> getBlogsByUser(@PathVariable int userId) {
        List<Blog> blogs = blogService.getBlogsByUser(userId);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
}
