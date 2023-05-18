package com.java.order.phone.api.exceptions;

public class OrderAPIException extends RuntimeException{
    public OrderAPIException(String errorMessage) {
        super(errorMessage);
    }
}
