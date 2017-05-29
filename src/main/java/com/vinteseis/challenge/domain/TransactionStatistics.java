package com.vinteseis.challenge.domain;

import com.vinteseis.challenge.domain.dtos.TransactionStatisticsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentMap;

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

    public void update(ConcurrentMap<Long, TimestampTransactions> transactions) {
        long oneMinuteBefore = currentTimeMillis() - A_MINUTE;
        restartValues();

        transactions.forEach( (timestamp, timestampTransactions) -> {
            if (timestamp > oneMinuteBefore) {
                updateValues(timestampTransactions);
            }
        });
    }

    public TransactionStatisticsDto toDto() {
        double updatedSum = sum;
        double updatedAvg = avg;
        long updatedCount = count;
        double updatedMax = max == MIN_VALUE ? 0 : max;
        double updatedMin = min == MAX_VALUE ? 0 : min;

        return new TransactionStatisticsDto(updatedSum, updatedAvg, updatedMax, updatedMin, updatedCount);
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
