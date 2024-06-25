package com.xuanduy.chc_backend.service;

import com.xuanduy.chc_backend.dto.request.ImageRequest;
import com.xuanduy.chc_backend.entity.Image;

import java.util.List;

public interface ImageService {
    Image createImage(ImageRequest imageRequest);

    List<Image> getAllImages();

    Image updateImage(String imageId, ImageRequest imageRequest);

    void deleteImage(String imageId);

}
