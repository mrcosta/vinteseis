package com.vinteseis.challenge.domain;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.vinteseis.challenge.domain.TransactionStatistics.A_MINUTE;
import static java.lang.System.currentTimeMillis;

@Component
@Getter
public class Transactions {

    private Map<Long, Transaction> transactions;

    public Transactions() {
       transactions = new HashMap<>();
    }

    public long add(Transaction transaction) {
        long count = 0l;

        if (isInTheLastMinute(transaction.getTimestamp())) { // TODO: move to a validation of the input level
            if (timestampAlreadyAdded(transaction.getTimestamp()) && isInTheLastMinute(transaction.getTimestamp())) {
                count = mergeTransactions(transaction);
            } else {
                count = addTransaction(transaction);
            }
        }

        return count;
    }

    private boolean isInTheLastMinute(long timestamp) {
        long now = currentTimeMillis();
        long oneMinuteBefore = now - A_MINUTE;
        return timestamp >= oneMinuteBefore && timestamp <= now;
    }

    private boolean timestampAlreadyAdded(long timestamp) {
        return transactions.containsKey(timestamp);
    }

    private long addTransaction(Transaction transaction) {
        transactions.put(transaction.getTimestamp(), transaction);
        transaction.incrementCounter();

        return transaction.getCount();
    }

    private long mergeTransactions(Transaction transaction) {
        Transaction existingTransaction = transactions.get(transaction.getTimestamp());
        existingTransaction.addAmount(transaction.getAmount());
        existingTransaction.incrementCounter();

        return existingTransaction.getCount();
    }
}
