package com.springboot.contapp.services;

import com.springboot.contapp.controllers.InvoicesController;
import com.springboot.contapp.models.Invoice;
import com.springboot.contapp.repositories.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = InvoicesController.class)
@AutoConfigureMockMvc(addFilters = false)
class TestInvoiceService {


    @Test
    void getInvoiceByIdTest() {
        InvoiceRepository mockRepository = mock(InvoiceRepository.class);
        when(mockRepository.findInvoiceById(anyInt())).thenReturn(new Invoice());
        InvoiceService invoiceService = new InvoiceService();
        invoiceService.setInvoiceRepository(mockRepository);
        Invoice invoice = invoiceService.getInvoiceById(100);
        assertNotNull(invoice);
    }

    @Test
    void throwExceptionForNonExistingInvoice() {
        InvoiceRepository mockRepository = mock(InvoiceRepository.class);
        when(mockRepository.findInvoiceById(anyInt())).thenReturn(null);
        InvoiceService invoiceService = new InvoiceService();
        invoiceService.setInvoiceRepository(mockRepository);
        try {
            invoiceService.getInvoiceById(100);
            fail("Exception should have been thrown");
        } catch (Exception e) {
            assertEquals("Invoice with id: 100 not found.", e.getMessage());
        }
    }

}
