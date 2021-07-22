package com.sergsnmail.springdata.exception;

import com.sergsnmail.springdata.exception.product.ProductError;
import com.sergsnmail.springdata.exception.product.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ProductNotFoundException e) {
        log.error(e.getMessage());
        ProductError err = new ProductError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
