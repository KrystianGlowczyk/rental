package com.krglow.rental.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krglow.rental.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    String exampleCompanyJson = "{ \"dateFrom\": \"2022-05-16\", \"dateTo\": \"2022-05-17\", \"id_facility\": 2, \"id_landlord\": 1, \"id_tenant\": 3}";

    @Test
    @Transactional
    void createReservation() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8080/reservations")
                .accept(MediaType.APPLICATION_JSON).content(exampleCompanyJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();



        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }
}
