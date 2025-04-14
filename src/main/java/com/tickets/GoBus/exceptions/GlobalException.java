package com.tickets.GoBus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

public class GlobalException {
@ExceptionHandler(BusesException.class)
    public ResponseEntity<ErrorDetails> ProductExceptionHandler(BusesException pe, WebRequest req){
    ErrorDetails errorDetails=new ErrorDetails();
    errorDetails.setDetals(req.getDescription(false));
    errorDetails.setError(pe.getMessage());
    errorDetails.setTimeStamp(LocalDateTime.now());

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
}
}
