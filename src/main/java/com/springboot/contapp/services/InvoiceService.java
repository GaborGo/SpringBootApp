package com.springboot.contapp.services;

import com.springboot.contapp.exceptions.InvoiceNotFoundException;
import com.springboot.contapp.models.Invoice;
import com.springboot.contapp.repositories.InvoiceRepository;
import com.springboot.contapp.services.dto.InvoiceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public void saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    public void saveInvoices(List<Invoice> invoices) {
        invoices.forEach(n -> invoiceRepository.save(n));
    }

    public void deleteInvoiceById(int id) {
        invoiceRepository.deleteInvoiceById(id);
    }

    public Invoice getInvoiceById(int id) {
        Invoice invoice = invoiceRepository.findInvoiceById(id);
        if (invoice == null)
            throw new InvoiceNotFoundException("Invoice with id: " + id + " not found.");
        return invoice;
    }

    public List<Invoice> getInvoicesByIdList(List<Integer> idList) {
        return idList.stream().map(this::getInvoiceById).collect(Collectors.toList());
    }

    public InvoiceDto convertToInvoiceDto(Invoice invoice) {
        return modelMapper.map(invoice, InvoiceDto.class);
    }

    public Invoice convertToInvoice(InvoiceDto invoiceDto) {
        return modelMapper.map(invoiceDto, Invoice.class);
    }

    public List<InvoiceDto> convertToInvoiceDtoList(List<Invoice> invoices) {
        return invoices.stream()
                .map(this::convertToInvoiceDto)
                .collect(Collectors.toList());
    }
}
