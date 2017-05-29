package com.vinteseis.challenge.controllers;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.lang.System.currentTimeMillis;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsControllerIntegrationTest {

    public static final int TEN_SECONDS = 10000;

    @Autowired
    private MockMvc mvc;

    @Test
    @Ignore
    public void shouldReturnTransactionStatisticsEvenWithoutAnySentTransactions() throws Exception {
        String statistics = "{\"sum\":0.0,\"avg\":0.0,\"max\":0.0,\"min\":0.0,\"count\":0}";

        mvc.perform(get("/statistics").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(statistics)));
    }

    @Test
    public void shouldMergeValidTransactionsAndReturnCorrectStatistics() throws Exception {
        String statistics = "{\"sum\":132.1,\"avg\":44.03,\"max\":50.7,\"min\":30.7,\"count\":3}";
        long timestamp = currentTimeMillis();

        String previousTransaction = "{\"amount\": 50.7,\"timestamp\": " + (currentTimeMillis() - TEN_SECONDS) + "}";
        String transaction = "{\"amount\": 30.7,\"timestamp\": " + timestamp + "}";
        String otherTransaction = "{\"amount\": 50.7,\"timestamp\": " + timestamp + "}";

        mvc.perform(post("/transactions").content(previousTransaction).contentType(APPLICATION_JSON));
        Thread.sleep(100);
        mvc.perform(post("/transactions").content(transaction).contentType(APPLICATION_JSON));
        Thread.sleep(100);
        mvc.perform(post("/transactions").content(otherTransaction).contentType(APPLICATION_JSON));
        Thread.sleep(100);

        mvc.perform(get("/statistics").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(statistics)));
    }
}