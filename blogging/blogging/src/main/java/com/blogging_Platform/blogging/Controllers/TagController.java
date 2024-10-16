package com.blogging_Platform.blogging.Controllers;

import com.blogging_Platform.blogging.models.Tag;
import com.blogging_Platform.blogging.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    // Create Tag
    @Operation(summary = "Create a new Tag", description = "This endpoint allows creating a new tag in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tag created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag createdTag = tagService.createtag(tag);
        return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
    }

    // Get All Tags
    @Operation(summary = "Get all tags", description = "This endpoint fetches a list of all tags.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tags retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No tags found")
    })
    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagService.getAllTag();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    // Update Tag by ID
    @Operation(summary = "Update an existing Tag", description = "This endpoint allows updating an existing tag by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag updated successfully"),
            @ApiResponse(responseCode = "404", description = "Tag not found")
    })
    @PutMapping("/{tagId}")
    public ResponseEntity<Tag> updateTag(@PathVariable int tagId, @RequestBody Tag updatedTag) {
        Optional<Tag> tagOptional = tagService.updateTag(tagId, updatedTag);
        return tagOptional.map(tag -> new ResponseEntity<>(tag, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete Tag by ID
    @Operation(summary = "Delete a Tag", description = "This endpoint deletes a tag by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tag deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Tag not found")
    })
    @DeleteMapping("/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable int tagId) {
        boolean isDeleted = tagService.deleteTag(tagId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found
        }
    }
}
