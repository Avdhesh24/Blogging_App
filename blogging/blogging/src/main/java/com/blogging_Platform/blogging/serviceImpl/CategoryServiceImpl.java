package com.blogging_Platform.blogging.serviceImpl;

import com.blogging_Platform.blogging.models.Category;
import com.blogging_Platform.blogging.repository.CategoryRepository;
import com.blogging_Platform.blogging.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(int categoryId, Category categoryDetails) {
        return categoryRepository.findById(categoryId).map(category -> {
            // Check if the new category name already exists (excluding the current category)
            if (categoryRepository.findByName(categoryDetails.getName()).isPresent()) {
                throw new RuntimeException("Category with the same name already exists");
            }
            category.setName(categoryDetails.getName());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);

        if (existingCategory.isPresent()) {
            // Log deletion action
            System.out.println("Deleting category: " + existingCategory.get().getName());
            categoryRepository.delete(existingCategory.get());
            return true;
        }
        return false;
    }
}
