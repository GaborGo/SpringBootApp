package com.springboot.contapp.services.dto;

import com.springboot.contapp.models.Invoice;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class PaymentDto {

    private int id;
    private String type;
    private Date date;
    private int value;

    Set<Invoice> invoices = new HashSet<>();

    public PaymentDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }
}
