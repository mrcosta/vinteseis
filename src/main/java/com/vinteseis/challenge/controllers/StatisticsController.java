package com.vinteseis.challenge.controllers;

import com.vinteseis.challenge.domain.TransactionStatistics;
import com.vinteseis.challenge.domain.dtos.TransactionStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    private TransactionStatistics transactionStatistics;

    @Autowired
    public StatisticsController(TransactionStatistics transactionStatistics) {
        this.transactionStatistics = transactionStatistics;
    }

    @RequestMapping("/statistics")
    @ResponseBody
    public TransactionStatisticsDto getStatistics() {
        return transactionStatistics.toDto();
    }
}
