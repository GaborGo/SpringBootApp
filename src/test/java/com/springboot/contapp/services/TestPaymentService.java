package com.springboot.contapp.services;


import com.springboot.contapp.controllers.PaymentsController;
import com.springboot.contapp.models.Payment;
import com.springboot.contapp.repositories.PaymentRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PaymentsController.class)
@AutoConfigureMockMvc(addFilters = false)
class TestPaymentService {

    @Test
    void getPaymentByIdTest() {
        PaymentRepository mockRepository = mock(PaymentRepository.class);
        when(mockRepository.findPaymentById(anyInt())).thenReturn(new Payment());
        PaymentService paymentService = new PaymentService();
        paymentService.setPaymentRepository(mockRepository);
        Payment payment = paymentService.findPaymentById(100);
        assertNotNull(payment);
    }

    @Test
    void throwExceptionForNonExistingPayment() {
        PaymentRepository mockRepository = mock(PaymentRepository.class);
        when(mockRepository.findPaymentById(anyInt())).thenReturn(null);
        PaymentService paymentService = new PaymentService();
        paymentService.setPaymentRepository(mockRepository);
        try {
            paymentService.findPaymentById(100);
            fail("Exception should have been thrown");
        } catch (Exception e) {
            assertEquals("Payment with id: 100 not found.", e.getMessage());
        }
    }

}
