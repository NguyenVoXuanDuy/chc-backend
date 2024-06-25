package com.xuanduy.chc_backend.service.impl;

import com.xuanduy.chc_backend.dto.request.CategoryRequest;
import com.xuanduy.chc_backend.entity.Category;
import com.xuanduy.chc_backend.exception.AppException;
import com.xuanduy.chc_backend.exception.ErrorCode;
import com.xuanduy.chc_backend.mapper.CategoryMapper;
import com.xuanduy.chc_backend.repository.CategoryRepository;
import com.xuanduy.chc_backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Category createCategory(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByName(categoryRequest.getName())) {
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        }
        Category category = categoryMapper.toCategory(categoryRequest);
        return categoryRepository.save(category);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Category updateCategory(String categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        categoryMapper.updateCategory(category, categoryRequest);
        return categoryRepository.save(category);
    }
}
