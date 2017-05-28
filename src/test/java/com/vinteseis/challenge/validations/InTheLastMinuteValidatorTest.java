package com.vinteseis.challenge.validations;

import com.vinteseis.challenge.domain.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.*;
import java.util.Set;

import static java.lang.System.currentTimeMillis;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class InTheLastMinuteValidatorTest {

    private static final int TEN_SECONDS = 10000;

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void shouldBeValidWhenTimestampIsInTheCurrentMinute() throws Exception {
        Set<ConstraintViolation<Transaction>> violations = validator.validate(new Transaction(20, currentTimeMillis()));

        assertThat(violations.size(), is(0));
    }

    @Test
    public void shouldThrowInvalidTimestampExceptionWhenTimestampIsOlderThan60Seconds() throws Exception {
        Set<ConstraintViolation<Transaction>> violations = validator.validate(new Transaction(20, 1495889815650l));

        assertThat(violations.size(), is(1));
        ConstraintViolation<Transaction> violation = violations.iterator().next();
        assertEquals(violation.getMessage(), "timestamp older than 60 seconds or is in the future");
        assertEquals(violation.getPropertyPath().toString(), "timestamp");
        assertEquals(violation.getInvalidValue(), 1495889815650l);
    }

    @Test
    public void shouldThrowInvalidTimestampExceptionWhenTimestampIsInTheFuture() throws Exception {
        long timestamp = currentTimeMillis() + TEN_SECONDS;
        Set<ConstraintViolation<Transaction>> violations = validator.validate(new Transaction(20, timestamp));

        assertThat(violations.size(), is(1));
        ConstraintViolation<Transaction> violation = violations.iterator().next();
        assertEquals(violation.getMessage(), "timestamp older than 60 seconds or is in the future");
        assertEquals(violation.getPropertyPath().toString(), "timestamp");
        assertEquals(violation.getInvalidValue(), timestamp);
    }
}