package com.hospitalmanagement.exception;

public class NotAllowedException extends RuntimeException {
    public NotAllowedException(){
        super("Operation not permitted.");
    }
}
