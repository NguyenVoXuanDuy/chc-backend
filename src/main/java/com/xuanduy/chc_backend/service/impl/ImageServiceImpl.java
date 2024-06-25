package com.xuanduy.chc_backend.service.impl;

import com.xuanduy.chc_backend.dto.request.ImageRequest;
import com.xuanduy.chc_backend.entity.Image;
import com.xuanduy.chc_backend.exception.AppException;
import com.xuanduy.chc_backend.exception.ErrorCode;
import com.xuanduy.chc_backend.mapper.ImageMapper;
import com.xuanduy.chc_backend.repository.ImageRepository;
import com.xuanduy.chc_backend.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Image createImage(ImageRequest imageRequest) {
        if (imageRepository.existsByUrl(imageRequest.getUrl())) {
            throw new AppException(ErrorCode.IMAGE_EXISTED);
        }
        Image image = imageMapper.toImage(imageRequest);
        return imageRepository.save(image);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteImage(String imageId) {
        imageRepository.deleteById(imageId);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Image updateImage(String imageId, ImageRequest imageRequest) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new AppException(ErrorCode.IMAGE_NOT_FOUND));

        imageMapper.updateImage(image, imageRequest);
        return imageRepository.save(image);
    }

}
