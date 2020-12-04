package com.springboot.contapp.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @Column(name = "invoiceId")
    private int id;
    private int number;
    private Date emitDate;
    private Date scadentDate;
    private int value;
    private int sold;

    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    @ManyToMany
    @JoinTable(name = "invoice_payments",
    joinColumns = @JoinColumn(name = "invoice_id"),
    inverseJoinColumns = @JoinColumn(name = "payment_id"))
    private List<Payment> payments = new ArrayList<>();

    public Invoice() {}

    public Invoice(int id, int number, Date emitDate, Date scadentDate, int value, int sold) {
        this.id = id;
        this.number = number;
        this.emitDate = emitDate;
        this.scadentDate = scadentDate;
        this.value = value;
        this.sold = sold;
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

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
