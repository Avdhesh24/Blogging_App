package com.blogging_Platform.blogging.serviceImpl;

import com.blogging_Platform.blogging.models.Blog;
import com.blogging_Platform.blogging.models.Comment;
import com.blogging_Platform.blogging.repository.BlogRepository;
import com.blogging_Platform.blogging.repository.CommentRepository;
import com.blogging_Platform.blogging.repository.UserRepository;
import com.blogging_Platform.blogging.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment addCommentToBlog(int blogId, Comment comment) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        comment.setBlog(blog);

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsForBlog(int blogId) {
        return commentRepository.findByBlog_Id(blogId);
    }

    @Override
    public Comment editComment(int commentId, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        existingComment.setContent(updatedComment.getContent());
        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteComment(int commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }
}
