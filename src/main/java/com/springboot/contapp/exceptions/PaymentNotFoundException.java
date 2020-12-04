package com.springboot.contapp.exceptions;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
