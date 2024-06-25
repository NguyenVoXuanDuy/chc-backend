package com.xuanduy.chc_backend.service.impl;

import com.xuanduy.chc_backend.dto.request.BrandRequest;
import com.xuanduy.chc_backend.entity.Brand;
import com.xuanduy.chc_backend.exception.AppException;
import com.xuanduy.chc_backend.exception.ErrorCode;
import com.xuanduy.chc_backend.mapper.BrandMapper;
import com.xuanduy.chc_backend.repository.BrandRepository;
import com.xuanduy.chc_backend.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Brand createBrand(BrandRequest brandRequest) {
        if (brandRepository.existsByName(brandRequest.getName())) {
            throw new AppException(ErrorCode.BRAND_EXISTED);
        }
        Brand brand = brandMapper.toBrand(brandRequest);
        return brandRepository.save(brand);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Brand updateBrand(String brandId, BrandRequest brandRequest) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));

        brandMapper.updateBrand(brand, brandRequest);
        return brandRepository.save(brand);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteBrand(String brandId) {
        brandRepository.deleteById(brandId);
    }
}
