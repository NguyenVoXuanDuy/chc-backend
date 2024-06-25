package com.xuanduy.chc_backend.validation;

import com.xuanduy.chc_backend.exception.ErrorCode;
import jakarta.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LengthValidator.class)
public @interface LengthConstraint {
    String message() default "Invalid size";

    ErrorCode errorCode() default ErrorCode.INVALID_SIZE;

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

    int min() default -1;

    int max() default -1;
}
