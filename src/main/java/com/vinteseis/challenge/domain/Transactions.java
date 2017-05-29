package com.vinteseis.challenge.domain;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Getter
public class Transactions {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final int EACH_10_MILLISECONDS = 10;

    @Autowired
    private TransactionStatistics transactionStatistics;

    private ConcurrentMap<Long, TimestampTransactions> transactions;

    public Transactions() {
       transactions = new ConcurrentHashMap<>();
    }

    public long add(Transaction transaction) {
        long count;

        if (timestampAlreadyAdded(transaction.getTimestamp())) {
            count = mergeTransactions(transaction);
        } else {
            count = addTransaction(transaction);
        }

        return count;
    }

    @Scheduled(fixedDelay = EACH_10_MILLISECONDS)
    public void updateStatistics() {
        transactionStatistics.update(transactions);
    }

    private boolean timestampAlreadyAdded(long timestamp) {
        return transactions.containsKey(timestamp);
    }

    private long addTransaction(Transaction transaction) {
        TimestampTransactions timestampTransactions = new TimestampTransactions(transaction.getTimestamp(), transaction.getAmount());

        transactions.put(transaction.getTimestamp(), timestampTransactions);
        updateInformation(timestampTransactions, transaction.getAmount());

        return timestampTransactions.getCount();
    }

    private long mergeTransactions(Transaction transaction) {
        TimestampTransactions existingTimestampTransactions = transactions.get(transaction.getTimestamp());
        existingTimestampTransactions.addAmount(transaction.getAmount());
        updateInformation(existingTimestampTransactions, transaction.getAmount());

        return existingTimestampTransactions.getCount();
    }

    private void updateInformation(TimestampTransactions timestampTransactions, double amount) {
        timestampTransactions.incrementCounter();
        timestampTransactions.chooseCurrentMax(amount);
        timestampTransactions.chooseCurrentMin(amount);
    }
}
