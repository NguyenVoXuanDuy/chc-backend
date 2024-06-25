package com.xuanduy.chc_backend.service.impl;

import com.xuanduy.chc_backend.dto.request.ProductRequest;
import com.xuanduy.chc_backend.entity.Product;
import com.xuanduy.chc_backend.exception.AppException;
import com.xuanduy.chc_backend.exception.ErrorCode;
import com.xuanduy.chc_backend.mapper.ProductMapper;
import com.xuanduy.chc_backend.repository.BrandRepository;
import com.xuanduy.chc_backend.repository.CategoryRepository;
import com.xuanduy.chc_backend.repository.ImageRepository;
import com.xuanduy.chc_backend.repository.ProductRepository;
import com.xuanduy.chc_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ImageRepository imageRepository;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Product createProduct(ProductRequest productRequest) {
        if (productRepository.existsByName(productRequest.getName())) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }

        Product product = productMapper.toProduct(productRequest);
        product.setCategory(categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND)));

        product.setBrand(brandRepository.findById(productRequest.getBrandId())
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND)));

        product.setImages(new HashSet<>(imageRepository.findAllById(productRequest.getImageIds())));
        if (product.getImages().isEmpty()) {
            throw new AppException(ErrorCode.IMAGE_REQUIRED);
        }
        return productRepository.save(product);
    }

    @Override
    //todo add pagination and search by name later
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        System.out.println("ProductServiceImpl.getProductById");
        return productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Product updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        if (!product.getName().equals(productRequest.getName()) && productRepository.existsByName(productRequest.getName())) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }

        product.setCategory(categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND)));


        product.setBrand(brandRepository.findById(productRequest.getBrandId())
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND)));

        product.setImages(new HashSet<>(imageRepository.findAllById(productRequest.getImageIds())));
        if (productRequest.getImageIds().isEmpty()) {
            throw new AppException(ErrorCode.IMAGE_REQUIRED);
        }

        productMapper.updateProduct(product, productRequest);

        return productRepository.save(product);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
