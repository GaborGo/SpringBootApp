package com.springboot.contapp.controllers;

import com.springboot.contapp.models.Invoice;
import com.springboot.contapp.services.dto.InvoiceDto;
import com.springboot.contapp.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("invoices")
public class InvoicesController {

    private InvoiceService invoiceService;
    private static final String REDIRECT_INVOICES = "redirect:/invoices";

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @GetMapping("")
    public String getInvoicesHome(Model model) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        model.addAttribute("invoices", invoiceService.convertToInvoiceDtoList(invoices));
        model.addAttribute("newInvoice", new InvoiceDto());
        return "invoices";
    }

    @GetMapping("deleteInvoice/{id}")
    public String deleteInvoice(@PathVariable("id") int id) {
        invoiceService.deleteInvoiceById(id);
        return REDIRECT_INVOICES;
    }

    @GetMapping("updateInvoice/{id}")
    public String updateInvoice(@PathVariable("id") int id, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        model.addAttribute("invoice", invoiceService.convertToInvoiceDto(invoice));
        return "updateInvoice";
    }

    @PostMapping("addInvoice")
    public String addInvoice(@ModelAttribute("invoice") InvoiceDto invoiceDto) {
        Invoice invoice = invoiceService.convertToInvoice(invoiceDto);
        invoiceService.saveInvoice(invoice);
        return REDIRECT_INVOICES;
    }

    @PostMapping("saveInvoice")
    public String saveInvoice(@ModelAttribute("invoice") InvoiceDto invoiceDto) {
        Invoice invoice = invoiceService.getInvoiceById(invoiceDto.getId());
        Invoice updatedInvoice = invoiceService.convertToInvoice(invoiceDto);
        updatedInvoice.setPayments(invoice.getPayments());
        invoiceService.saveInvoice(updatedInvoice);
        return REDIRECT_INVOICES;
    }
}
