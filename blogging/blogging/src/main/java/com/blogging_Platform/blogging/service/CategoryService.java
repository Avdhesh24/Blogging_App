package com.blogging_Platform.blogging.service;

import com.blogging_Platform.blogging.models.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getAllCategory();

    Category updateCategory(int categoryId, Category categoryDetails);

    boolean deleteCategory(int categoryId);
}
