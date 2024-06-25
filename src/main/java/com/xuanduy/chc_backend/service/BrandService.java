package com.xuanduy.chc_backend.service;

import com.xuanduy.chc_backend.dto.request.BrandRequest;
import com.xuanduy.chc_backend.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand createBrand(BrandRequest brandRequest);

    List<Brand> getAllBrands();

    Brand updateBrand(String brandId, BrandRequest brandRequest);

    void deleteBrand(String brandId);

}
