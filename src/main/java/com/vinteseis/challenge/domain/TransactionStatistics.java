package com.vinteseis.challenge.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.System.currentTimeMillis;

@Getter
@NoArgsConstructor
public class TransactionStatistics {

    public static final int MILLISECONDS_IN_A_MINUTE = 60000;
    
    private double sum;
    private double avg;
    private double max;
    private double min;
    private long count;

    public TransactionStatistics updateStatistics(Map<Long, Transaction> transactions) {
        long oneMinuteBefore = currentTimeMillis() - MILLISECONDS_IN_A_MINUTE;
        restartValues();

        transactions.forEach( (timestamp, transaction) -> {
            if (transaction.getTimestamp() > oneMinuteBefore) {
                updateValues(transaction);
            }
        });

        return this;
    }

    private void updateValues(Transaction transaction) {
        sum += transaction.getAmount();
        count += transaction.getCount();
        avg = sum / count;

        if (transaction.getAmount() > max) {
            max = transaction.getAmount();
        } else if (transaction.getAmount() < min) {
           min = transaction.getAmount();
        }
    }

    private void restartValues() {
        sum = 0;
        avg = 0;
        max = NEGATIVE_INFINITY;
        min = POSITIVE_INFINITY;
        count = 0;
    }
}
