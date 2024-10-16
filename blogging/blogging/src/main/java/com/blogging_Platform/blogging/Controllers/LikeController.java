package com.blogging_Platform.blogging.Controllers;

import com.blogging_Platform.blogging.models.Like;
import com.blogging_Platform.blogging.service.LikeService;
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
public class LikeController {

    @Autowired
    private LikeService likeService;

    // Like a Blog
    @Operation(summary = "Like a blog", description = "Allows a user to like a specific blog post by providing the blog ID and user ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Blog liked successfully"),
            @ApiResponse(responseCode = "404", description = "Blog or user not found")
    })
    @PostMapping("/{blogId}/likes")
    public ResponseEntity<Like> likeBlog(@PathVariable int blogId, @RequestParam int userId) {
        Like likedBlog = likeService.likeBlog(blogId, userId);
        if (likedBlog != null) {
            return new ResponseEntity<>(likedBlog, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if the blog or user is not found
    }

    // Get Likes for Blog
    @Operation(summary = "Get likes for a blog", description = "Retrieves all likes for a specific blog post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Likes retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Blog not found")
    })
    @GetMapping("/{blogId}/likes")
    public ResponseEntity<List<Like>> getLikesForBlog(@PathVariable int blogId) {
        List<Like> likes = likeService.getLikesForBlog(blogId);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    // Remove Like from Blog
    @Operation(summary = "Remove like from a blog", description = "Allows a user to remove their like from a specific blog post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Like removed successfully"),
            @ApiResponse(responseCode = "404", description = "Like or blog not found")
    })
    @DeleteMapping("/{blogId}/likes/{userId}")
    public ResponseEntity<Void> removeLikeFromBlog(@PathVariable int blogId, @PathVariable int userId) {
        boolean isRemoved = likeService.removeLikeFromBlog(blogId, userId);
        if (isRemoved) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return 204 if the like is successfully removed
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if the like wasn't found
    }
}
