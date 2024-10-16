package com.blogging_Platform.blogging.service;

import com.blogging_Platform.blogging.models.Comment;

import java.util.List;

public interface CommentService {

    Comment addCommentToBlog(int blogId, Comment comment);

    List<Comment> getCommentsForBlog(int blogId);

    Comment editComment(int commentId,Comment updatedComment);

    void deleteComment(int commentId);

}
