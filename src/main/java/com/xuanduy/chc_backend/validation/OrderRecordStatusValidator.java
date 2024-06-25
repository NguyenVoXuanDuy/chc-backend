package com.xuanduy.chc_backend.validation;

import com.xuanduy.chc_backend.enums.OrderStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OrderRecordStatusValidator implements ConstraintValidator<OrderRecordStatusConstraint, String> {
    @Override
    public void initialize(OrderRecordStatusConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null)
            return true;
        return s.equals(OrderStatus.PENDING.toString())
                || s.equals(OrderStatus.CANCELLED.toString())
                || s.equals(OrderStatus.CONFIRMED.toString())
                || s.equals(OrderStatus.COMPLETED.toString());
    }
}
