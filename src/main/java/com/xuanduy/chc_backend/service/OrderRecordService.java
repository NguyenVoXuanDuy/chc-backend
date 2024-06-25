package com.xuanduy.chc_backend.service;

import com.xuanduy.chc_backend.dto.request.OrderRecordCreationRequest;
import com.xuanduy.chc_backend.dto.request.OrderStatusUpdateRequest;
import com.xuanduy.chc_backend.entity.OrderRecord;

import java.util.List;

public interface OrderRecordService {
    OrderRecord createOrderRecord(OrderRecordCreationRequest orderCreationRequest);

    List<OrderRecord> getAllOrderRecords();

    List<OrderRecord> getOrderRecordsByUserId(String userId);


    OrderRecord updateOrderRecordStatus(String orderId, OrderStatusUpdateRequest orderStatusUpdateRequest);
}
