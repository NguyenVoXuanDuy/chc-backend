package com.xuanduy.chc_backend.controller;

import com.xuanduy.chc_backend.dto.request.ProductRequest;
import com.xuanduy.chc_backend.dto.response.ApiResponse;
import com.xuanduy.chc_backend.entity.Product;
import com.xuanduy.chc_backend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController()
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("")
    ApiResponse<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        System.out.println("ProductController.createProduct");
        return ApiResponse.<Product>builder()
                .message("Product created")
                .result(productService.createProduct(productRequest))
                .build();
    }

    @GetMapping("")
    ApiResponse<List<Product>> getAllProducts() {
        System.out.println("ProductController.createProduct");
        return ApiResponse.<List<Product>>builder()
                .message("Get all products")
                .result(productService.getAllProducts())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<Product> getProductById(@PathVariable String id) {
        return ApiResponse.<Product>builder()
                .message("Get product by id")
                .result(productService.getProductById(id))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<Product> updateProduct(@PathVariable String id, @RequestBody @Valid ProductRequest productRequest) {
        return ApiResponse.<Product>builder()
                .message("Product updated")
                .result(productService.updateProduct(id, productRequest))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ApiResponse.<String>builder()
                .message("Product deleted")
                .result("Deleted")
                .build();
    }
}
