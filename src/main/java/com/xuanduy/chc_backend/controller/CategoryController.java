package com.xuanduy.chc_backend.controller;

import com.xuanduy.chc_backend.dto.request.CategoryRequest;
import com.xuanduy.chc_backend.dto.response.ApiResponse;
import com.xuanduy.chc_backend.entity.Category;
import com.xuanduy.chc_backend.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController()
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    ApiResponse<Category> createProduct(@RequestBody @Valid CategoryRequest categoryRequest) {
        return ApiResponse.<Category>builder()
                .message("Category created")
                .result(categoryService.createCategory(categoryRequest))
                .build();
    }

    @GetMapping("")
    ApiResponse<List<Category>> getAllCategories() {
        return ApiResponse.<List<Category>>builder()
                .message("Get all categories")
                .result(categoryService.getAllCategories())
                .build();
    }

    @DeleteMapping("/{categoryId}")
    ApiResponse<String> deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
        return ApiResponse.<String>builder()
                .message("Category deleted")
                .result("Category deleted")
                .build();
    }

    @PutMapping("/{categoryId}")
    ApiResponse<Category> updateCategory(@PathVariable String categoryId, @RequestBody @Valid CategoryRequest categoryRequest) {
        return ApiResponse.<Category>builder()
                .message("Category updated")
                .result(categoryService.updateCategory(categoryId, categoryRequest))
                .build();
    }
}
