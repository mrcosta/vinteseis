package com.vinteseis.challenge.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TransactionStatisticsDto {

    private double sum;
    private double avg;
    private double max;
    private double min;
    private long count;
}
