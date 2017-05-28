package com.vinteseis.challenge.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.lang.System.currentTimeMillis;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldExcludeAdditionalInputsAndAddAValidTransaction() throws Exception {
        long timestamp = currentTimeMillis();
        String transaction = "{\"amount\": 50.7,\"timestamp\": " + timestamp + ",\"max\":0.0}";

        mvc.perform(post("/transactions")
                .content(transaction)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(isEmptyOrNullString()));
    }

    @Test
    public void shouldNotAddTransactionWithOlderTimestamps() throws Exception {
        String transaction = "{\"amount\": 50.7,\"timestamp\": 1495889815650 }";

        mvc.perform(post("/transactions")
                .content(transaction)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(isEmptyOrNullString()));
    }

    @Test
    public void shouldNotAddTransactionWithWrongInputs() throws Exception {
        String transaction = "{\"amount\": a,\"timestamp\": b }";

        mvc.perform(post("/transactions")
                .content(transaction)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(isEmptyOrNullString()));
    }
}