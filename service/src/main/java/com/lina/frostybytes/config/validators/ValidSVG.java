package com.lina.frostybytes.config.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

@Documented
@Constraint(validatedBy = ValidSVGValidator.class)
@Target({ FIELD, METHOD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSVG {
    String message() default "Invalid SVG string";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
