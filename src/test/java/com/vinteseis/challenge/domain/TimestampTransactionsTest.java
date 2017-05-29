package com.vinteseis.challenge.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TimestampTransactionsTest {

    @Test
    public void shouldIncrementTransactionCounter() {
        TimestampTransactions timestampTransactions = new TimestampTransactions(1495889815650l, 12.3);
        assertThat(timestampTransactions.incrementCounter(), is(1l));
    }

    @Test
    public void shouldAddAmountToPreviousOne() {
        TimestampTransactions timestampTransactions = new TimestampTransactions(1495889815650l, 12.3);
        assertThat(timestampTransactions.addAmount(20), is(32.3));
    }

    @Test
    public void shouldReturnNewMaxWhenMaxOfCurrentTransactionIsBigger() {
        TimestampTransactions timestampTransactions = new TimestampTransactions(1495889815650l, 12.3, 12.3, 12.3, 1);
        assertThat(timestampTransactions.chooseCurrentMax(35), is(35d));
    }

    @Test
    public void shouldReturnCurrentMaxWhenMaxOfCurrentTransactionIsSmaller() {
        TimestampTransactions timestampTransactions = new TimestampTransactions(1495889815650l, 12.3, 12.3, 12.3, 1);
        assertThat(timestampTransactions.chooseCurrentMax(5), is(12.3d));
    }

    @Test
    public void shouldReturnNewMinWhenMinOfCurrentTransactionIsSmaller() {
        TimestampTransactions timestampTransactions = new TimestampTransactions(1495889815650l, 12.3, 12.3, 12.3, 1);
        assertThat(timestampTransactions.chooseCurrentMin(5), is(5d));
    }

    @Test
    public void shouldReturnCurrentMinWhenMinOfCurrentTransactionIsBigger() {
        TimestampTransactions timestampTransactions = new TimestampTransactions(1495889815650l, 12.3, 12.3, 12.3, 1);
        assertThat(timestampTransactions.chooseCurrentMin(20), is(12.3d));
    }

    @Test
    public void shouldReturnCurrentMinWhenIsTheFirstTransaction() {
        TimestampTransactions timestampTransactions = new TimestampTransactions(1495889815650l, 12.3);
        assertThat(timestampTransactions.chooseCurrentMin(12.3), is(12.3d));
    }
}