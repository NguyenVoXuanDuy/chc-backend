package com.xuanduy.chc_backend.repository;

import com.xuanduy.chc_backend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    boolean existsByUrl(String url);
}
