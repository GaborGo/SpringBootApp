package com.springboot.contapp.services.dto;
import com.springboot.contapp.models.Payment;
import com.springboot.contapp.models.Supplier;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class InvoiceDto {

    private int id;
    private int number;
    private Date emitDate;
    private Date scadentDate;
    private int value;
    private int sold;
    private Supplier supplier;
    private Set<Payment> payments = new HashSet<>();

    public InvoiceDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getEmitDate() {
        return emitDate;
    }

    public void setEmitDate(Date emitDate) {
        this.emitDate = emitDate;
    }

    public Date getScadentDate() {
        return scadentDate;
    }

    public void setScadentDate(Date scadentDate) {
        this.scadentDate = scadentDate;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
