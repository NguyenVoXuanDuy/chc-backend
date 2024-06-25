package com.xuanduy.chc_backend.controller;

import com.xuanduy.chc_backend.dto.request.CartUpdateRequest;
import com.xuanduy.chc_backend.dto.response.ApiResponse;
import com.xuanduy.chc_backend.entity.Cart;
import com.xuanduy.chc_backend.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController()
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cardService;

    @PutMapping("/{userId}")
    ApiResponse<Cart> updateCart(@PathVariable String userId, @RequestBody @Valid CartUpdateRequest cartUpdateRequest) {
        return ApiResponse.<Cart>builder()
                .message("Cart updated")
                .result(cardService.updateCart(userId, cartUpdateRequest))
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<Cart> getCart(@PathVariable String userId) {
        return ApiResponse.<Cart>builder()
                .message("Cart retrieved")
                .result(cardService.getCart(userId))
                .build();
    }
}
