package com.xuanduy.chc_backend.dto.request;

import com.xuanduy.chc_backend.validation.LengthConstraint;
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
public class ProductRequest {

    @NotNullConstraint
    @NotBlankConstraint
    private String name;

    @NotNullConstraint
    @NotBlankConstraint
    private String description;

    @NotNullConstraint
    @NotBlankConstraint
    private Integer price;

    @NotNullConstraint
    @NotBlankConstraint
    private String categoryId;

    @NotNullConstraint
    @NotBlankConstraint
    private String brandId;


    @LengthConstraint(min = 1)
    @NotNullConstraint
    private List<String> imageIds;

}
