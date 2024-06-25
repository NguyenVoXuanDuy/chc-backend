package com.xuanduy.chc_backend.dto.request;

import com.xuanduy.chc_backend.validation.LengthConstraint;
import com.xuanduy.chc_backend.validation.NotBlankConstraint;
import com.xuanduy.chc_backend.validation.NotNullConstraint;
import com.xuanduy.chc_backend.validation.PhoneConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdateRequest {
    @NotNullConstraint
    @NotBlankConstraint
    @LengthConstraint(max = 20)
    private String firstName;

    @NotNullConstraint
    @NotBlankConstraint
    @LengthConstraint(max = 20)
    private String lastName;

    @NotNullConstraint
    @NotBlankConstraint
    @PhoneConstraint
    private String phone;

    @NotNullConstraint
    List<String> roleIds;
}
