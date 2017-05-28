package com.vinteseis.challenge.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vinteseis.challenge.validations.InTheLastMinute;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@AllArgsConstructor
public class Transaction {

    @NotNull
    private double amount;

    @NotNull
    @InTheLastMinute
    private long timestamp;

    private long count;

    public Transaction(double amount, long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public long incrementCounter() {
        return ++count;
    }

    public double addAmount(double amountToAdd) {
        return amount += amountToAdd;
    }
}
