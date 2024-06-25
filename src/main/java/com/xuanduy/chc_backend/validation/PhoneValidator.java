package com.xuanduy.chc_backend.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<PhoneConstraint, Object> {
    private static final String PHONE_NUMBER_REGEX = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";

    public boolean isValidPhoneNumber(String phoneNumber) {
        // Remove all non-digit characters
        String cleanedPhoneNumber = phoneNumber.replaceAll("\\D", "");
        // Validate the cleaned phone number against the regex pattern
        return Pattern.matches(PHONE_NUMBER_REGEX, cleanedPhoneNumber);
    }

    @Override
    public void initialize(PhoneConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null)
            return true;
        if (!(o instanceof String)) return true;
        String s = String.valueOf(o);
        return isValidPhoneNumber(s);
    }


}
