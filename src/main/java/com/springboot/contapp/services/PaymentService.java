package com.springboot.contapp.services;

import com.springboot.contapp.exceptions.PaymentNotFoundException;
import com.springboot.contapp.models.Invoice;
import com.springboot.contapp.models.Payment;
import com.springboot.contapp.repositories.PaymentRepository;
import com.springboot.contapp.services.dto.PaymentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private InvoiceService invoiceService;
    private ModelMapper modelMapper;

    @Autowired
    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void deletePaymentById(int id) {
        paymentRepository.deletePaymentById(id);
    }

    public Payment findPaymentById(int id) {
        Payment payment = paymentRepository.findPaymentById(id);
        if (payment == null)
            throw new PaymentNotFoundException("Payment with id: " + id + " not found.");
        return payment;
    }

    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public void removePayment(Payment payment) {
        List<Invoice> invoices = payment.getInvoices();
        invoices.forEach(n -> n.getPayments().remove(payment));
        invoiceService.saveInvoices(invoices);
        deletePaymentById(payment.getId());
    }

    public void bindPaymentToInvoices(Payment payment, List<Invoice> invoices) {
        payment.getInvoices().addAll(invoices);
        savePayment(payment);
        invoices.forEach(n -> n.getPayments().add(payment));
        invoiceService.saveInvoices(invoices);
    }

    public PaymentDto convertToPaymentDto(Payment payment) {
        return modelMapper.map(payment, PaymentDto.class);
    }

    public Payment convertToPayment(PaymentDto paymentDto) {
        return modelMapper.map(paymentDto, Payment.class);
    }

    public List<PaymentDto> convertToPaymentDtoList(List<Payment> payments) {
        return payments.stream()
                .map(this::convertToPaymentDto)
                .collect(Collectors.toList());
    }
}
