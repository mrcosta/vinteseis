package com.vinteseis.challenge.domain;

import org.junit.Test;

import static java.lang.System.currentTimeMillis;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TransactionsTest {

    public static final int TEN_SECONDS = 10000;

    @Test
    public void shouldAddTransactionsAndReturnItsNumberOfOccurrencesBasedInItsTimestamps() throws Exception {
        Transaction transaction = new Transaction(12.3, currentTimeMillis() - 100);
        Transaction otherTransaction = new Transaction(20, currentTimeMillis() - 200);

        Transactions transactions = new Transactions();
        long countTransaction = transactions.add(transaction);
        long countOtherTransaction = transactions.add(otherTransaction);

        assertThat(transactions.getTransactions().size(), is(2));
        assertThat(countTransaction, is(1l));
        assertThat(countOtherTransaction, is(1l));
    }

    @Test
    public void shouldMergeTransactionsIfGivenTimestampWasAlreadySent() throws Exception {
        long timestamp = currentTimeMillis() - TEN_SECONDS;
        Transaction transaction = new Transaction(12.3, timestamp);
        Transaction otherTransaction = new Transaction(20, timestamp);

        Transactions transactions = new Transactions();
        transactions.add(transaction);
        long count = transactions.add(otherTransaction);

        assertThat(transactions.getTransactions().size(), is(1));
        assertThat(transactions.getTransactions().get(timestamp).getAmount(), is(32.3));
        assertThat(transactions.getTransactions().get(timestamp).getMax(), is(20.0));
        assertThat(transactions.getTransactions().get(timestamp).getMin(), is(12.3));
        assertThat(count, is(2l));
    }
}