package com.xuanduy.chc_backend.service;

import com.xuanduy.chc_backend.dto.request.ProductRequest;
import com.xuanduy.chc_backend.entity.Product;

import java.util.List;


public interface ProductService {
    Product createProduct(ProductRequest productRequest);

    List<Product> getAllProducts();

    Product getProductById(String id);

    Product updateProduct(String id, ProductRequest productRequest);

    void deleteProduct(String id);
}
