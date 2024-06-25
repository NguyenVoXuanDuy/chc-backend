package com.xuanduy.chc_backend.mapper;

import com.xuanduy.chc_backend.dto.request.OrderRecordCreationRequest;
import com.xuanduy.chc_backend.entity.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderRecordMapper {

    @Mapping(target = "orderDetails", ignore = true)
    OrderRecord toOrderRecord(OrderRecordCreationRequest orderRecordRequest);

    OrderRecord toOrder(OrderRecordCreationRequest orderCreationRequest);
}
