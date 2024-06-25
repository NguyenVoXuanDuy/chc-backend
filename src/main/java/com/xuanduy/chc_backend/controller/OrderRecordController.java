package com.xuanduy.chc_backend.controller;

import com.xuanduy.chc_backend.dto.request.OrderRecordCreationRequest;
import com.xuanduy.chc_backend.dto.request.OrderStatusUpdateRequest;
import com.xuanduy.chc_backend.dto.response.ApiResponse;
import com.xuanduy.chc_backend.entity.OrderRecord;
import com.xuanduy.chc_backend.service.OrderRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController()
@RequiredArgsConstructor
@RequestMapping("/order-records")
public class OrderRecordController {
    private final OrderRecordService orderRecordService;

    @PostMapping("")
    ApiResponse<OrderRecord> createPermission(@RequestBody @Valid OrderRecordCreationRequest OrderRecordCreationRequest) {
        return ApiResponse.<OrderRecord>builder()
                .message("Order record created")
                .result(orderRecordService.createOrderRecord(OrderRecordCreationRequest))
                .build();
    }

    @GetMapping("")
    ApiResponse<List<OrderRecord>> getAllOrderRecords() {
        return ApiResponse.<List<OrderRecord>>builder()
                .message("Get all order records")
                .result(orderRecordService.getAllOrderRecords())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<List<OrderRecord>> getOrderRecordById(@PathVariable String userId) {
        return ApiResponse.<List<OrderRecord>>builder()
                .message("Get order record by id")
                .result(orderRecordService.getOrderRecordsByUserId(userId))
                .build();
    }

    @PutMapping("/update-status/{orderId}")
    ApiResponse<OrderRecord> updateOrderRecordStatus(@PathVariable String orderId, @RequestBody @Valid OrderStatusUpdateRequest orderStatusUpdateRequest) {
        return ApiResponse.<OrderRecord>builder()
                .message("Order record status updated")
                .result(orderRecordService.updateOrderRecordStatus(orderId, orderStatusUpdateRequest))
                .build();

    }
}
