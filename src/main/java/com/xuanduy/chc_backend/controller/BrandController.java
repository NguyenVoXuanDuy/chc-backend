package com.xuanduy.chc_backend.controller;

import com.xuanduy.chc_backend.dto.request.BrandRequest;
import com.xuanduy.chc_backend.dto.response.ApiResponse;
import com.xuanduy.chc_backend.entity.Brand;
import com.xuanduy.chc_backend.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController()
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @PostMapping("")
    ApiResponse<Brand> createBrand(@RequestBody @Valid BrandRequest brandRequest) {
        return ApiResponse.<Brand>builder()
                .message("Brand created")
                .result(brandService.createBrand(brandRequest))
                .build();
    }

    @GetMapping("")
    ApiResponse<List<Brand>> getAllBrands() {
        return ApiResponse.<List<Brand>>builder()
                .message("Get all brands")
                .result(brandService.getAllBrands())
                .build();
    }

    @DeleteMapping("/{brandId}")
    ApiResponse<Void> deleteBrand(@PathVariable String brandId) {
        brandService.deleteBrand(brandId);
        return ApiResponse.<Void>builder()
                .message("Brand deleted")
                .build();
    }

    @PutMapping("/{brandId}")
    ApiResponse<Brand> updateBrand(@PathVariable String brandId, @RequestBody @Valid BrandRequest brandRequest) {
        return ApiResponse.<Brand>builder()
                .message("Brand updated")
                .result(brandService.updateBrand(brandId, brandRequest))
                .build();
    }
}
