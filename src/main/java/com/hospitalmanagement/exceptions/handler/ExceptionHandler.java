package com.hospitalmanagement.exceptions.handler;

import com.hospitalmanagement.exceptions.ErrorDetailsResponse;
import com.hospitalmanagement.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {


    //Handle Specific Exception
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsResponse> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    //Handle Exception Globally
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsResponse> handleGlobalException(Exception exception, WebRequest webRequest){
       ErrorDetailsResponse errorDetailsResponse =  new  ErrorDetailsResponse(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsResponse, HttpStatus.NOT_FOUND);
    }
}

