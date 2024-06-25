package com.xuanduy.chc_backend.validation;

import com.xuanduy.chc_backend.exception.ErrorCode;
import jakarta.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
public @interface PhoneConstraint {
    String message() default "Invalid phone number";

    ErrorCode errorCode() default ErrorCode.INVALID_FIELD;

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
