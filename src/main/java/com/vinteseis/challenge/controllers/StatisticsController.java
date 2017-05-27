package com.vinteseis.challenge.controllers;

import com.vinteseis.challenge.domain.TransactionStatistics;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    @RequestMapping("/statistics")
    @ResponseBody
    public TransactionStatistics getStatistics() {
        return new TransactionStatistics(1);
    }
}
