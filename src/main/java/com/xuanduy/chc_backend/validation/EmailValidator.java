package com.xuanduy.chc_backend.validation;

import jakarta.validation.ConstraintValidator;

public class EmailValidator implements ConstraintValidator<EmailConstraint, Object> {

    @Override
    public void initialize(EmailConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        if (o == null)
            return true;
        if (!(o instanceof String)) return true;
        String s = String.valueOf(o);
        org.apache.commons.validator.routines.EmailValidator emailValidator = org.apache.commons.validator.routines.EmailValidator.getInstance();
        return emailValidator.isValid(s);
    }
}
