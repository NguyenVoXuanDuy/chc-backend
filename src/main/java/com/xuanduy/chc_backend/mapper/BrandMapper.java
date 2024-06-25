package com.xuanduy.chc_backend.mapper;

import com.xuanduy.chc_backend.dto.request.BrandRequest;
import com.xuanduy.chc_backend.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toBrand(BrandRequest brandRequest);

    void updateBrand(@MappingTarget Brand brand, BrandRequest brandRequest);
}
