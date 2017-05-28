package com.vinteseis.challenge.controllers;

import com.vinteseis.challenge.domain.Transaction;
import com.vinteseis.challenge.domain.Transactions;
import org.junit.Test;

import static java.lang.System.currentTimeMillis;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TransactionControllerTest {

    @Test
    public void shouldAddAValidTransaction() {
        Transaction transaction = new Transaction(20.0, currentTimeMillis());
        Transactions transactions = new Transactions();
        TransactionController transactionController = new TransactionController(transactions);

        transactionController.registerTransaction(transaction);
        assertThat(transactions.getTransactions().size(), is(1));
    }
}