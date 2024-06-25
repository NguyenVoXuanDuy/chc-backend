package com.xuanduy.chc_backend.dto.request;

import com.xuanduy.chc_backend.validation.NotBlankConstraint;
import com.xuanduy.chc_backend.validation.NotNullConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest {
    @NotNullConstraint
    @NotBlankConstraint
    private String productId;

    @NotNullConstraint
    @NotBlankConstraint
    private Integer quantity;
    
}
