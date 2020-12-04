package com.springboot.contapp.exceptions;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
