package com.xuanduy.chc_backend.mapper;

import com.xuanduy.chc_backend.dto.request.CategoryRequest;
import com.xuanduy.chc_backend.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest categoryRequest);

    void updateCategory(@MappingTarget Category category, CategoryRequest categoryRequest);

}
