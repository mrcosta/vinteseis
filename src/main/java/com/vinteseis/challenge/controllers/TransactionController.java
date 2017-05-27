package com.vinteseis.challenge.controllers;

import com.vinteseis.challenge.domain.Transaction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TransactionController {

    @RequestMapping(method = POST, value = "/transactions")
    public void registerTransaction(@RequestBody Transaction transaction) {
//        System.out.println(Instant.ofEpochMilli(transaction.getTimestamp()).atZone(ZoneOffset.UTC));
        System.out.println(transaction);
    }
}
