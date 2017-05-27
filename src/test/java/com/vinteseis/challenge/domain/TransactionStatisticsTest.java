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

    public static final int TWO_SECONDS = 2000;
    public static final int TEN_SECONDS = 10000;
    public static final int THIRTY_SECONDS = 30000;

    @Test
    public void shouldUpdateStatisticsExcludingTheOnesThatAreOlderThan60Seconds() {
        Transaction olderTransaction = new Transaction(10.1, 1495889815650l, 1);
        Transaction actualTransaction = new Transaction(13.1, currentTimeMillis(), 2);

        TransactionStatistics transactionStatistics = new TransactionStatistics();
        transactionStatistics.updateStatistics(createTransactions(asList(olderTransaction, actualTransaction)));

        assertThat(transactionStatistics.getCount(), is(2l));
        assertThat(transactionStatistics.getSum(), is(13.1));
    }

    @Test
    public void shouldUpdateStatisticsInformationCorrectly() {
        Transaction olderTransaction = new Transaction(10.1, 1495889815650l, 1);
        Transaction transaction = new Transaction(1.1, currentTimeMillis() - TWO_SECONDS, 2);
        Transaction transaction2 = new Transaction(13.2, currentTimeMillis() - TEN_SECONDS, 2);
        Transaction transaction3 = new Transaction(13.1, currentTimeMillis() - THIRTY_SECONDS, 2);

        TransactionStatistics transactionStatistics = new TransactionStatistics();
        transactionStatistics.updateStatistics(createTransactions(asList(olderTransaction, transaction, transaction2, transaction3)));

        assertThat(transactionStatistics.getCount(), is(6l));
        assertThat(transactionStatistics.getSum(), is(27.4));
        assertThat(transactionStatistics.getAvg(), is(4.566666666666666));
        assertThat(transactionStatistics.getMax(), is(13.2));
        assertThat(transactionStatistics.getMin(), is(1.1));
    }

    private Map<Long, Transaction> createTransactions(List<Transaction> transactionsToAdd) {
        return transactionsToAdd.stream().collect(toMap(Transaction::getTimestamp, transaction -> transaction));
    }
}