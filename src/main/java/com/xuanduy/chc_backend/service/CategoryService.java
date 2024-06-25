package com.xuanduy.chc_backend.service;

import com.xuanduy.chc_backend.dto.request.CategoryRequest;
import com.xuanduy.chc_backend.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryRequest categoryRequest);

    List<Category> getAllCategories();

    void deleteCategory(String categoryId);

    Category updateCategory(String categoryId, CategoryRequest categoryRequest);
}
