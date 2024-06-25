package com.xuanduy.chc_backend.dto.request;

import com.xuanduy.chc_backend.validation.NotBlankConstraint;
import com.xuanduy.chc_backend.validation.NotNullConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleRequest {
    @NotNullConstraint
    @NotBlankConstraint
    private String name;

    @NotNullConstraint
    @NotBlankConstraint
    private String description;

    @NotNullConstraint
    private Set<String> permissionIds = new HashSet<>();
}
