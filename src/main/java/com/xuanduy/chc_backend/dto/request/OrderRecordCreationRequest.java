package com.xuanduy.chc_backend.dto.request;

import com.xuanduy.chc_backend.validation.NotBlankConstraint;
import com.xuanduy.chc_backend.validation.NotNullConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRecordCreationRequest {
    @NotNullConstraint
    @NotBlankConstraint
    private String userId;

    @NotNullConstraint
    @NotBlankConstraint
    private String address;

    @NotNullConstraint
    @NotBlankConstraint
    private List<OrderDetailRequest> orderDetails;
}
