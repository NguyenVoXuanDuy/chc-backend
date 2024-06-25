package com.xuanduy.chc_backend.dto.request;

import com.xuanduy.chc_backend.validation.NotBlankConstraint;
import com.xuanduy.chc_backend.validation.NotNullConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartUpdateRequest {
    @NotNullConstraint
    @NotBlankConstraint
    List<CartDetailRequest> cartDetailRequests;
}
