package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.response.CounterResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CounterControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCounterAndExpectOk() throws Exception {
        assertThat(this.mockMvc
                .perform(get("/counter"))
                .andDo(print())
                .andExpect(status().isOk())
        );
    }

    @Test
    void incrementCounterAndExpectOk() throws Exception {
        assertThat(this.mockMvc
                .perform(post("/counter")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"increment\": 1}"))
                .andDo(print())
                .andExpect(status().isOk())
        );
    }

    @Test
    void incrementNegativeAndExpectBadRequest() throws Exception {
        assertThat(this.mockMvc
                .perform(post("/counter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"increment\": -10}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
        );
    }

    @Test
    void incrementAngGetCounterAndExpectIncremented() throws Exception {
        Long increment = 1L;

        CounterResponse response = objectMapper.readValue(this.mockMvc
                .perform(get("/counter"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(),
        CounterResponse.class);

        this.mockMvc
                .perform(post("/counter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"increment\": " + increment + "}"))
                .andDo(print())
                .andExpect(status().isOk());


        CounterResponse incrementedResponse = objectMapper.readValue(this.mockMvc
                .perform(get("/counter"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(),
        CounterResponse.class);

        assertEquals(response.getValue() + increment, incrementedResponse.getValue());
    }
}