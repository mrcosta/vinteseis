package com.vinteseis.challenge.controllers;

import com.vinteseis.challenge.domain.Transaction;
import com.vinteseis.challenge.domain.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TransactionController {

    private Transactions transactions;

    @Autowired
    public TransactionController(Transactions transactions) {
        this.transactions = transactions;
    }

    @RequestMapping(method = POST, value = "/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerTransaction(@Valid @RequestBody Transaction transaction) {
        transactions.add(transaction);
    }
}
