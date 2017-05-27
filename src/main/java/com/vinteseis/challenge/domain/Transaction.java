package com.vinteseis.challenge.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Transaction {

    private double amount;
    private long timestamp;
    private long count;

    public Transaction(double amount, long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public long incrementCounter() {
        return ++count;
    }

    @Override
    public String toString() {
        return "Transaction {amount: " + amount + ", timestamp: " + timestamp + "}";
    }

    public double addAmount(double amountToAdd) {
        return amount += amountToAdd;
    }
}
