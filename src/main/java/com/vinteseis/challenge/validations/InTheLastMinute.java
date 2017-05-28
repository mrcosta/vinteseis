package com.vinteseis.challenge.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({ElementType.METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=InTheLastMinuteValidator.class)
public @interface InTheLastMinute {

    String message() default "timestamp older than 60 seconds or is in the future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
