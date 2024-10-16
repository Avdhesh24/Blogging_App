package com.blogging_Platform.blogging.service;


import com.blogging_Platform.blogging.models.Like;

import java.util.List;

public interface LikeService {

    Like likeBlog (int blogId,int userId);

    List<Like> getLikesForBlog(int blogId);

    boolean removeLikeFromBlog(int blogId, int userId);

}