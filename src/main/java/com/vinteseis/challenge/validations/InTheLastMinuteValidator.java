package com.vinteseis.challenge.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.lang.System.currentTimeMillis;

public class InTheLastMinuteValidator implements ConstraintValidator<InTheLastMinute, Long> {

    public static final int A_MINUTE = 60000;

    @Override
    public void initialize(InTheLastMinute inTheLastMinute) {
        // empty method following the contract
    }

    @Override
    public boolean isValid(Long timestamp, ConstraintValidatorContext context) {
        long now = currentTimeMillis();
        long oneMinuteBefore = now - A_MINUTE;

        return timestamp >= oneMinuteBefore && timestamp <= now;
    }
}
