package com.xuanduy.chc_backend.mapper;

import com.xuanduy.chc_backend.dto.request.OrderDetailRequest;
import com.xuanduy.chc_backend.entity.OrderDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetail toOrderDetail(OrderDetailRequest orderDetailRequest);
}
