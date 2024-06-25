package com.xuanduy.chc_backend.service.impl;

import com.xuanduy.chc_backend.dto.request.OrderDetailRequest;
import com.xuanduy.chc_backend.dto.request.OrderRecordCreationRequest;
import com.xuanduy.chc_backend.dto.request.OrderStatusUpdateRequest;
import com.xuanduy.chc_backend.entity.OrderDetail;
import com.xuanduy.chc_backend.entity.OrderRecord;
import com.xuanduy.chc_backend.entity.Product;
import com.xuanduy.chc_backend.entity.User;
import com.xuanduy.chc_backend.exception.AppException;
import com.xuanduy.chc_backend.exception.ErrorCode;
import com.xuanduy.chc_backend.mapper.OrderDetailMapper;
import com.xuanduy.chc_backend.mapper.OrderRecordMapper;
import com.xuanduy.chc_backend.repository.OrderRecordRepository;
import com.xuanduy.chc_backend.repository.ProductRepository;
import com.xuanduy.chc_backend.repository.UserRepository;
import com.xuanduy.chc_backend.service.OrderRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderRecordServiceImpl implements OrderRecordService {
    private final OrderRecordRepository orderRecordRepository;
    private final OrderRecordMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private Integer calculateTotalPrice(List<OrderDetailRequest> orderDetails) {
        int totalPrice = 0;
        for (OrderDetailRequest orderDetailRequest : orderDetails) {
            Product product = productRepository.findById(orderDetailRequest.getProductId()).
                    orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
            totalPrice += product.getPrice() * orderDetailRequest.getQuantity();
        }
        return totalPrice;
    }

    @Override
    //todo make url "/makeOrder" later that use information from SecurityContextHolder to get userId

    public OrderRecord createOrderRecord(OrderRecordCreationRequest orderCreationRequest) {
        OrderRecord orderRecord = orderMapper.toOrderRecord(orderCreationRequest);
        orderRecord.setTotalPrice(calculateTotalPrice(orderCreationRequest.getOrderDetails()));
        User user = userRepository.findById(orderCreationRequest.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        orderRecord.setUser(user);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderRecord.setStatus("PENDING");
        for (OrderDetailRequest orderDetailRequest : orderCreationRequest.getOrderDetails()) {
            Product product = productRepository.findById(orderDetailRequest.getProductId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
            OrderDetail orderDetail = orderDetailMapper.toOrderDetail(orderDetailRequest);
            orderDetail.setProduct(product);
            orderDetail.setPrice(product.getPrice());
            orderDetail.setOrderRecord(orderRecord);
            orderDetails.add(orderDetail);
        }

        orderRecord.setOrderDetails(orderDetails);
        return orderRecordRepository.save(orderRecord);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<OrderRecord> getAllOrderRecords() {
        return orderRecordRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<OrderRecord> getOrderRecordsByUserId(String userId) {
        return orderRecordRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public OrderRecord updateOrderRecordStatus(String orderId, OrderStatusUpdateRequest orderStatusUpdateRequest) {
        OrderRecord orderRecord = orderRecordRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        orderRecord.setStatus(orderStatusUpdateRequest.getStatus());
        return orderRecordRepository.save(orderRecord);
    }
}
