package com.xuanduy.chc_backend.validation;

import com.xuanduy.chc_backend.exception.ErrorCode;
import jakarta.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrderRecordStatusValidator.class)
public @interface OrderRecordStatusConstraint {
    String message() default "Invalid status";

    ErrorCode errorCode() default ErrorCode.STATUS_INVALID;

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
