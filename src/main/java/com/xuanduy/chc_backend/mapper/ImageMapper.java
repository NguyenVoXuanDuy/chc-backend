package com.xuanduy.chc_backend.mapper;

import com.xuanduy.chc_backend.dto.request.ImageRequest;
import com.xuanduy.chc_backend.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toImage(ImageRequest imageRequest);
    
    void updateImage(@MappingTarget Image image, ImageRequest imageRequest);
}
