package com.xuanduy.chc_backend.dto.request;

import com.xuanduy.chc_backend.validation.NotBlankConstraint;
import com.xuanduy.chc_backend.validation.NotNullConstraint;
import com.xuanduy.chc_backend.validation.OrderRecordStatusConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusUpdateRequest {
    @NotNullConstraint
    @NotBlankConstraint
    @OrderRecordStatusConstraint
    private String Status;
}
