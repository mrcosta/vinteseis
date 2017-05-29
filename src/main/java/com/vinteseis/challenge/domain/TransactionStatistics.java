package com.vinteseis.challenge.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Map;

import static java.lang.Double.*;
import static java.lang.System.currentTimeMillis;

@Getter
@NoArgsConstructor
@Component
public class TransactionStatistics {

    public static final int A_MINUTE = 60000;

    private double sum;
    private double avg;
    private double max = MIN_VALUE;
    private double min = MAX_VALUE;
    private long count;

    public void update(Map<Long, TimestampTransactions> transactions) {
        long oneMinuteBefore = currentTimeMillis() - A_MINUTE;
        restartValues();

        transactions.forEach( (timestamp, timestampTransactions) -> {
            if (timestamp > oneMinuteBefore) {
                updateValues(timestampTransactions);
            }
        });
    }

    public TransactionStatistics formatted() {
        max = max == MIN_VALUE ? 0 : max;
        min = min == MAX_VALUE ? 0 : min;

        return this;
    }

    private void updateValues(TimestampTransactions timestampTransactions) {
        DecimalFormat doubleTwoDecimals = new DecimalFormat(".##");

        sum = parseDouble(doubleTwoDecimals.format(timestampTransactions.getAmount() + sum));
        count += timestampTransactions.getCount();
        avg = parseDouble(doubleTwoDecimals.format(sum / count));

        if (timestampTransactions.getMax() > max) {
            max = timestampTransactions.getMax();
        }

        if (timestampTransactions.getMin() < min) {
           min = timestampTransactions.getMin();
        }
    }

    private void restartValues() {
        sum = 0;
        avg = 0;
        max = MIN_VALUE;
        min = MAX_VALUE;
        count = 0;
    }
}
