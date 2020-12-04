package com.springboot.contapp.controllers;

import com.springboot.contapp.models.Invoice;
import com.springboot.contapp.services.InvoiceService;
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
@WebMvcTest(controllers = InvoicesController.class)
@AutoConfigureMockMvc(addFilters = false)
class TestInvoicesController {

    @MockBean
    private InvoiceService mockService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSuccessFindAllInvoices() throws Exception {
        List<Invoice> invoices = Arrays.asList(new Invoice(), new Invoice());
        when(mockService.getAllInvoices()).thenReturn(invoices);
        this.mockMvc.perform(get("/invoices"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attribute("invoices",
                        mockService.convertToInvoiceDtoList(invoices)))
                .andExpect(MockMvcResultMatchers.view().name("invoices"));
    }
}
