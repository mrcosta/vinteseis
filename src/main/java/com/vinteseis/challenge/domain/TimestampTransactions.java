package com.vinteseis.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;

@Getter
@AllArgsConstructor
public class TimestampTransactions {

    private long timestamp;
    private double amount;
    private double max = MIN_VALUE;
    private double min = MAX_VALUE;
    private long count;

    public TimestampTransactions(long timestamp, double amount) {
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public double addAmount(double amountToAdd) {
        return amount += amountToAdd;
    }

    public long incrementCounter() {
        return ++count;
    }

    public double chooseCurrentMax(double amount) {
        max = (amount > max || max == MIN_VALUE) ? amount : max;
        return max;
    }

    public double chooseCurrentMin(double amount) {
        min = (amount < min || min == MAX_VALUE) ? amount : min;
        return min;
    }
}
