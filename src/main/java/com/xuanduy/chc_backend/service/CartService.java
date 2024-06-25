package com.xuanduy.chc_backend.service;

import com.xuanduy.chc_backend.dto.request.CartUpdateRequest;
import com.xuanduy.chc_backend.entity.Cart;

public interface CartService {


    Cart updateCart(String userId, CartUpdateRequest cartUpdateRequest);

    Cart getCart(String userId);
}
