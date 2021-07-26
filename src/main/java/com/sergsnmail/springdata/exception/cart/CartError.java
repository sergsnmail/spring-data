package com.sergsnmail.springdata.exception.cart;

import lombok.Data;

@Data
public class CartError {
    int errorCode;
    String message;

    public CartError(int errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }
}
