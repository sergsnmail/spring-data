package com.sergsnmail.springdata.exception.product;

import lombok.Data;

@Data
public class ProductError {
    int errorCode;
    String message;

    public ProductError(int errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }
}
