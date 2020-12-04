package com.springboot.contapp.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="suppliers")
public class Supplier {
    @Id
    @Column(name = "supplierId")
    private int id;
    private String name;
    private int firmId;
    @OneToMany(mappedBy = "supplier")
    private Set<Invoice> invoices;

    public Supplier() {}

    public Supplier(int id, String name, int firmId) {
        this.id = id;
        this.name = name;
        this.firmId = firmId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }
}
