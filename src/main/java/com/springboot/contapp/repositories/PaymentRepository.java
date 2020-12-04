package com.springboot.contapp.repositories;

import com.springboot.contapp.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Transactional
    void deletePaymentById(int id);

    Payment findPaymentById(int id);

}
