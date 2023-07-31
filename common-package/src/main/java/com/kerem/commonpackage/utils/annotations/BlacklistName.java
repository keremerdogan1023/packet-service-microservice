package com.kerem.commonpackage.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BlacklistNameValidator.class)
public @interface BlacklistName {
    String message() default "Name is blacklisted!!";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
