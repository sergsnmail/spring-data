package com.sergsnmail.springdata.exception.cart;

public class CartProductNotFoundException extends RuntimeException{
    public CartProductNotFoundException(String message) {
        super(message);
    }
}
