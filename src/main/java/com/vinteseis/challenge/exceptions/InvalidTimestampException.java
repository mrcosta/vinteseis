package com.vinteseis.challenge.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@ResponseStatus(value = NO_CONTENT, reason = "timestamp older than 60 seconds or is in the future")
public class InvalidTimestampException extends ValidationException {
}
