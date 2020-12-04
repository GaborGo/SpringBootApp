package com.springboot.contapp.repositories;

import com.springboot.contapp.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Transactional
    void deleteInvoiceById(int id);

    Invoice findInvoiceById(int id);

}
