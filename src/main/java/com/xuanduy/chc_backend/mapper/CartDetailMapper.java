package com.xuanduy.chc_backend.mapper;

import com.xuanduy.chc_backend.dto.request.CartDetailRequest;
import com.xuanduy.chc_backend.entity.CartDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartDetailMapper {
    CartDetail toCartDetail(CartDetailRequest cartDetailRequest);
}
