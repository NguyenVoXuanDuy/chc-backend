package com.xuanduy.chc_backend.controller;

import com.xuanduy.chc_backend.dto.request.ImageRequest;
import com.xuanduy.chc_backend.dto.response.ApiResponse;
import com.xuanduy.chc_backend.entity.Image;
import com.xuanduy.chc_backend.service.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController()
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("")
    ApiResponse<Image> createImage(@RequestBody @Valid ImageRequest imageRequest) {
        return ApiResponse.<Image>builder()
                .message("Image created")
                .result(imageService.createImage(imageRequest))
                .build();
    }

    @GetMapping("")
    ApiResponse<List<Image>> getAllImages() {
        return ApiResponse.<List<Image>>builder()
                .message("Get all images")
                .result(imageService.getAllImages())
                .build();
    }

    @DeleteMapping("/{imageId}")
    ApiResponse<String> deleteImage(@PathVariable String imageId) {
        imageService.deleteImage(imageId);
        return ApiResponse.<String>builder()
                .message("Image deleted")
                .result("Image deleted")
                .build();
    }

    @PutMapping("/{imageId}")
    ApiResponse<Image> updateImage(@PathVariable String imageId, @RequestBody @Valid ImageRequest imageRequest) {
        return ApiResponse.<Image>builder()
                .message("Image updated")
                .result(imageService.updateImage(imageId, imageRequest))
                .build();
    }
}