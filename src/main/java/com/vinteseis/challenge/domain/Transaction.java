package com.vinteseis.challenge.domain;

import com.vinteseis.challenge.validations.InTheLastMinute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @NotNull
    private double amount;

    @NotNull
    @InTheLastMinute
    private long timestamp;
}
