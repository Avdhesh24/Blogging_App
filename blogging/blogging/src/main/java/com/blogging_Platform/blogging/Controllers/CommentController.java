package com.blogging_Platform.blogging.Controllers;

import com.blogging_Platform.blogging.models.Comment;
import com.blogging_Platform.blogging.service.CommentService;
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
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Add Comment to Blog
    @Operation(summary = "Add a comment to a blog", description = "Allows users to add a comment to a specific blog post by blog ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment added successfully"),
            @ApiResponse(responseCode = "404", description = "Blog not found")
    })
    @PostMapping("/{blogId}/comments")
    public ResponseEntity<Comment> addCommentToBlog(@PathVariable int blogId, @RequestBody Comment comment) {
        try {
            Comment newComment = commentService.addCommentToBlog(blogId, comment);
            return new ResponseEntity<>(newComment, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if blog not found
        }
    }

    // Get Comments for Blog
    @Operation(summary = "Get all comments for a blog", description = "Fetches all comments for a specific blog post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully")
    })
    @GetMapping("/{blogId}/comments")
    public ResponseEntity<List<Comment>> getCommentsForBlog(@PathVariable int blogId) {
        List<Comment> comments = commentService.getCommentsForBlog(blogId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Edit Comment
    @Operation(summary = "Edit a comment", description = "Allows users to edit their comment by providing the comment ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment edited successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Comment> editComment(@PathVariable int commentId, @RequestBody Comment updatedComment) {
        try {
            Comment editedComment = commentService.editComment(commentId, updatedComment);
            return new ResponseEntity<>(editedComment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if comment not found
        }
    }

    // Delete Comment
    @Operation(summary = "Delete a comment", description = "Allows users to delete a comment by providing the comment ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int commentId) {
        try {
            commentService.deleteComment(commentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 when deleted successfully
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if comment not found
        }
    }
}
