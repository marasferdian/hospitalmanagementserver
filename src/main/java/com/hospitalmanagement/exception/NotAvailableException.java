package com.hospitalmanagement.exception;

public class NotAvailableException extends RuntimeException {
    public NotAvailableException(){
        super("Already occupied");
    }
}
