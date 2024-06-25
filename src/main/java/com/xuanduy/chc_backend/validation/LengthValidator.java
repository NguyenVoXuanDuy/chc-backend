package com.xuanduy.chc_backend.validation;


import jakarta.validation.ConstraintValidator;

import java.util.List;

public class LengthValidator implements ConstraintValidator<LengthConstraint, Object> {
    private int min;
    private int max;

    @Override
    public void initialize(LengthConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Object o, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        if (o == null)
            return true;
        if (!(o instanceof String) && !(o instanceof List))
            return true;
        return o instanceof String ? isValidString((String) o) : isValidList((List<String>) o);
    }

    public boolean isValidList(List<String> list) {
        if (list == null)
            return true;
        if (min == -1 && max == -1)
            return true;
        if (min == -1)
            return list.size() <= max;
        if (max == -1)
            return list.size() >= min;
        return list.size() >= min && list.size() <= max;
    }

    public boolean isValidString(String s) {
        if (s == null)
            return true;
        if (min == -1 && max == -1)
            return true;
        if (min == -1)
            return s.length() <= max;
        if (max == -1)
            return s.length() >= min;
        return s.length() >= min && s.length() <= max;
    }
}
