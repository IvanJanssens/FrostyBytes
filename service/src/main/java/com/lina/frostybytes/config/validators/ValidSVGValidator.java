package com.lina.frostybytes.config.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidSVGValidator implements ConstraintValidator<ValidSVG, String> {
    private static final Pattern SVG_PATTERN = Pattern.compile("<svg[\\s\\S]*</svg>");

    @Override
    public void initialize(ValidSVG constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return SVG_PATTERN.matcher(value).matches();
    }
}