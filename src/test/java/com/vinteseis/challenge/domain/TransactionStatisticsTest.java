package com.vinteseis.challenge.domain;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TransactionStatisticsTest {

    private static final int TWO_SECONDS = 2000;
    private static final int TEN_SECONDS = 10000;
    private static final int THIRTY_SECONDS = 30000;

    @Test
    public void shouldUpdateStatisticsExcludingTheOnesThatAreOlderThan60Seconds() {
        TimestampTransactions olderTransaction = new TimestampTransactions(1495889815650l, 10.1, 1, 1, 1);
        TimestampTransactions actualTransaction = new TimestampTransactions(currentTimeMillis(), 13.1, 1, 1, 2);

        TransactionStatistics transactionStatistics = new TransactionStatistics();
        transactionStatistics.update(createTransactions(asList(olderTransaction, actualTransaction)));

        assertThat(transactionStatistics.getCount(), is(2l));
        assertThat(transactionStatistics.getSum(), is(13.1));
    }

    @Test
    public void shouldUpdateStatisticsInformationCorrectly() {
        TimestampTransactions olderTransaction = new TimestampTransactions(1495889815650l, 10.1,10.1, 10.1, 1);
        TimestampTransactions transaction = new TimestampTransactions(currentTimeMillis() - TWO_SECONDS, 1.1, 1.1, 1.1, 2);
        TimestampTransactions transaction2 = new TimestampTransactions(currentTimeMillis() - TEN_SECONDS, 13.2, 13.2, 13.2, 2);
        TimestampTransactions transaction3 = new TimestampTransactions(currentTimeMillis() - THIRTY_SECONDS, 13.1, 13.1, 13.1, 2);

        TransactionStatistics transactionStatistics = new TransactionStatistics();
        transactionStatistics.update(createTransactions(asList(olderTransaction, transaction, transaction2, transaction3)));

        assertThat(transactionStatistics.getCount(), is(6l));
        assertThat(transactionStatistics.getSum(), is(27.4));
        assertThat(transactionStatistics.getAvg(), is(4.57));
        assertThat(transactionStatistics.getMax(), is(13.2));
        assertThat(transactionStatistics.getMin(), is(1.1));
    }

    @Test
    public void shouldReturnTransactionStatisticsFormatted() {
        TransactionStatistics transactionStatistics = new TransactionStatistics();

        TransactionStatistics transactionStatisticsFormatted = transactionStatistics.formatted();

        assertThat(transactionStatisticsFormatted.getMax(), is(0.0));
        assertThat(transactionStatisticsFormatted.getMin(), is(0.0));
    }

    private Map<Long, TimestampTransactions> createTransactions(List<TimestampTransactions> transactionsToAdd) {
        return transactionsToAdd.stream().collect(toMap(TimestampTransactions::getTimestamp, timestampTransactions -> timestampTransactions));
    }
}