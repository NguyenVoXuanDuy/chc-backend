package com.xuanduy.chc_backend.repository;

import com.xuanduy.chc_backend.entity.OrderRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRecordRepository extends JpaRepository<OrderRecord, String> {
    Optional<List<OrderRecord>> findByUserId(String userId);
}
