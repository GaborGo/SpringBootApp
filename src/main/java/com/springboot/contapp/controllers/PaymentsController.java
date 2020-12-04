package com.springboot.contapp.controllers;

import com.springboot.contapp.models.Invoice;
import com.springboot.contapp.models.Payment;
import com.springboot.contapp.services.dto.PaymentDto;
import com.springboot.contapp.services.InvoiceService;
import com.springboot.contapp.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("payments")
public class PaymentsController {

    private PaymentService paymentService;
    private InvoiceService invoiceService;
    private static final String REDIRECT_PAYMENTS = "redirect:/payments";

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("")
    public String getPayments(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        List<Invoice> invoices = invoiceService.getAllInvoices();
        model.addAttribute("payments", paymentService.convertToPaymentDtoList(payments));
        model.addAttribute("invoices", invoiceService.convertToInvoiceDtoList(invoices));
        model.addAttribute("newPayment", new PaymentDto());
        return "payments";
    }

    @PostMapping("addPayment")
    public String addPayment(@RequestParam("invoiceId") List<Integer> idList, @ModelAttribute("newPayment") PaymentDto paymentDto) {
        List<Invoice> invoices = invoiceService.getInvoicesByIdList(idList);
        Payment payment = paymentService.convertToPayment(paymentDto);
        paymentService.bindPaymentToInvoices(payment, invoices);
        return REDIRECT_PAYMENTS;
    }

    @GetMapping("updatePayment/{id}")
    public String updatePayment(@PathVariable("id") int id, Model model) {
        Payment payment = paymentService.findPaymentById(id);
        List<Invoice> invoices = invoiceService.getAllInvoices();
        model.addAttribute("payment", paymentService.convertToPaymentDto(payment));
        model.addAttribute("invoices", invoiceService.convertToInvoiceDtoList(invoices));
        return "updatePayment";
    }

    @PostMapping("savePayment")
    public String savePayment(@RequestParam("invoiceId") List<Integer> idList,
                              @ModelAttribute("payment") PaymentDto paymentDto) {
        Payment payment = paymentService.findPaymentById(paymentDto.getId());
        paymentService.removePayment(payment);
        Payment updatedPayment = paymentService.convertToPayment(paymentDto);
        List<Invoice> updatedPaymentsInvoices = invoiceService.getInvoicesByIdList(idList);
        paymentService.bindPaymentToInvoices(updatedPayment, updatedPaymentsInvoices);
        return REDIRECT_PAYMENTS;
    }

    @GetMapping("deletePayment/{id}")
    public String deletePayment(@PathVariable("id") int id) {
        Payment payment = paymentService.findPaymentById(id);
        paymentService.removePayment(payment);
        return REDIRECT_PAYMENTS;
    }
}
