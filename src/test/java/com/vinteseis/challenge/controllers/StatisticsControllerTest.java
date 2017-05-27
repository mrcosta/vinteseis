package com.vinteseis.challenge.controllers;

import com.vinteseis.challenge.domain.TransactionStatistics;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StatisticsControllerTest {

    @Test
    public void shouldReturnTransactionsStatistics() {
        StatisticsController statisticsController = new StatisticsController(new TransactionStatistics());
        TransactionStatistics transactionStatistics = statisticsController.getStatistics();

        assertThat(transactionStatistics.getSum(), is(0.0));
        assertThat(transactionStatistics.getCount(), is(0l));
    }
}