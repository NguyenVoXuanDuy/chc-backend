package com.xuanduy.chc_backend.repository;

import com.xuanduy.chc_backend.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
