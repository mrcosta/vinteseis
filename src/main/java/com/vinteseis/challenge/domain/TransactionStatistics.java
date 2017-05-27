package com.vinteseis.challenge.domain;

import lombok.Getter;

@Getter
public class TransactionStatistics {

    private double sum;
    private double avg;
    private double max;
    private double min;
    private long count;

    public TransactionStatistics(long count) {
        this.count = count;
    }
}
