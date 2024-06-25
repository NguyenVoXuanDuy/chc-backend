package com.xuanduy.chc_backend.dto.request;

import com.xuanduy.chc_backend.validation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserCreationRequest {
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
    @EmailConstraint
    private String email;

    @NotNullConstraint
    @NotBlankConstraint
    @LengthConstraint(min = 6)
    private String password;

    @NotNullConstraint
    @NotBlankConstraint
    @PhoneConstraint
    private String phone;
}
