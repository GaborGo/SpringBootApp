package com.springboot.contapp.controllers;

import com.springboot.contapp.models.Payment;
import com.springboot.contapp.services.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PaymentsController.class)
@AutoConfigureMockMvc(addFilters = false)
class TestPaymentsController {

    @MockBean
    private PaymentService mockService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testSuccessFindAllPayments() throws Exception {
        List<Payment> payments = Arrays.asList(new Payment(), new Payment());
        when(mockService.getAllPayments()).thenReturn(payments);
        this.mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attribute("payments",
                        mockService.convertToPaymentDtoList(payments)))
                .andExpect(MockMvcResultMatchers.view().name("payments"));
    }

}
