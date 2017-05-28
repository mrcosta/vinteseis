package com.vinteseis.challenge.controllers;

import com.vinteseis.challenge.domain.Transaction;
import com.vinteseis.challenge.domain.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TransactionController {

    private Transactions transactions;

    @Autowired
    public TransactionController(Transactions transactions) {
        this.transactions = transactions;
    }

    @RequestMapping(method = POST, value = "/transactions")
    @Validated
    public void registerTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
