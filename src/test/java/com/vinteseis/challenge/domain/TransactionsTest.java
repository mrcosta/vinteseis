package com.vinteseis.challenge.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TransactionsTest {

    Transactions transactions = new Transactions();

    @Test
    public void shouldAddTransactionsAndReturnItsNumberOfOccurrencesBasedInItsTimestamps() throws Exception {
        Transaction transaction = new Transaction(12.3, 1495889815650l);
        Transaction otherTransaction = new Transaction(20, 1495889916291l);

        long countTransaction = transactions.add(transaction);
        long countOtherTransaction = transactions.add(otherTransaction);

        assertThat(transactions.getTransactions().size(), is(2));
        assertThat(countTransaction, is(1l));
        assertThat(countOtherTransaction, is(1l));
    }

    @Test
    public void shouldMergeTransactionsIfGivenTimestampWasAlreadySent() throws Exception {
        long timestamp = 1495889815650l;
        Transaction transaction = new Transaction(12.3, timestamp);
        Transaction otherTransaction = new Transaction(20, timestamp);

        transactions.add(transaction);
        long count = transactions.add(otherTransaction);

        assertThat(transactions.getTransactions().size(), is(1));
        assertThat(transactions.getTransactions().get(1495889815650l).getAmount(), is(32.3));
        assertThat(count, is(2l));
    }
}