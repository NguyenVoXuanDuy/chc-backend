package com.xuanduy.chc_backend.mapper;

import com.xuanduy.chc_backend.dto.request.ProductRequest;
import com.xuanduy.chc_backend.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "images", ignore = true)
    Product toProduct(ProductRequest request);

    @Mapping(target = "images", ignore = true)

        //Ignore images because ProductRequest images is list of string but Product images is list of Image
    void updateProduct(@MappingTarget Product product, ProductRequest request);
}
