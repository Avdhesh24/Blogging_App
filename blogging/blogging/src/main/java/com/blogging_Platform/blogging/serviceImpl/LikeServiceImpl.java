package com.blogging_Platform.blogging.serviceImpl;

import com.blogging_Platform.blogging.models.Like;
import com.blogging_Platform.blogging.repository.BlogRepository;
import com.blogging_Platform.blogging.repository.LikeRepository;
import com.blogging_Platform.blogging.repository.UserRepository;
import com.blogging_Platform.blogging.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Like likeBlog(int blogId, int userId) {
        return blogRepository.findById(blogId)
                .flatMap(blog -> userRepository.findById(userId)
                        .map(user -> {
                            Like like = new Like();
                            like.setBlog(blog);
                            like.setUser(user);
                            return likeRepository.save(like);
                        }))
                .orElse(null);
    }

    // Get all likes for a specific blog
    @Override
    public List<Like> getLikesForBlog(int blogId) {
        return likeRepository.findByBlog_Id(blogId);
    }

    // Remove like from blog
    @Override
    public boolean removeLikeFromBlog(int blogId, int userId) {
        return likeRepository.findByUser_IdAndBlog_Id(userId, blogId).stream()
                .findFirst()
                .map(like -> {
                    likeRepository.delete(like);
                    return true;
                }).orElse(false);
    }

}