package com.blogging_Platform.blogging.service;


import com.blogging_Platform.blogging.models.User;

import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    Optional<User> getUserProfile(int userId);

    Optional<User> updateUserProfile(int userId, User updatedUser);

    boolean deleteUser(int userId);

    Optional<User> getUserByUsername(String username);
}
