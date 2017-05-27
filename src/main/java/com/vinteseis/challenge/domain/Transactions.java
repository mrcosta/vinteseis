package com.vinteseis.challenge.domain;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class Transactions {

    private Map<Long, Transaction> transactions;

    public Transactions() {
       transactions = new HashMap<>();
    }

    public long add(Transaction transaction) {

        if (timestampAlreadyAdded(transaction.getTimestamp())) {
            return mergeTransactions(transaction);
        } else {
            return addTransaction(transaction);
        }
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

    private boolean timestampAlreadyAdded(long timestamp) {
        return transactions.containsKey(timestamp);
    }
}
