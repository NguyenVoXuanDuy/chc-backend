package com.xuanduy.chc_backend.service.impl;

import com.xuanduy.chc_backend.dto.request.CartDetailRequest;
import com.xuanduy.chc_backend.dto.request.CartUpdateRequest;
import com.xuanduy.chc_backend.entity.Cart;
import com.xuanduy.chc_backend.entity.CartDetail;
import com.xuanduy.chc_backend.entity.Product;
import com.xuanduy.chc_backend.exception.AppException;
import com.xuanduy.chc_backend.exception.ErrorCode;
import com.xuanduy.chc_backend.mapper.CartDetailMapper;
import com.xuanduy.chc_backend.repository.CartRepository;
import com.xuanduy.chc_backend.repository.ProductRepository;
import com.xuanduy.chc_backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartDetailMapper cartDetailMapper;
    private final ProductRepository productRepository;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cart updateCart(String userId, CartUpdateRequest cartUpdateRequest) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));


        for (CartDetailRequest cartDetailRequest : cartUpdateRequest.getCartDetailRequests()) {
            CartDetail cartDetail = cartDetailMapper.toCartDetail(cartDetailRequest);
            Product product = productRepository.findById(cartDetailRequest.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cart.addCartDetail(cartDetail);
        }
        return cartRepository.save(cart);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cart getCart(String userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
    }
}
