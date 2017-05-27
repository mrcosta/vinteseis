package com.vinteseis.challenge.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {

    @Test
    public void shouldIncrementTransactionCounter() {
        Transaction transaction = new Transaction(12.3, 1495889815650l);
        assertThat(transaction.incrementCounter(), is(1l));
    }

    @Test
    public void shouldAddAmountToPreviousOne() {
        Transaction transaction = new Transaction(12.3, 1495889815650l);
        assertThat(transaction.addAmount(20), is(32.3));
    }
}