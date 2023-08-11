package com.fnunezzz.pdfmicrosservice.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fnunezzz.pdfmicrosservice.exception.*;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage();

        if (errorMessageDescription == null) errorMessageDescription = ex.toString();

        HttpMethodNotSupportedResponse response = new HttpMethodNotSupportedResponse("", errorMessageDescription,  request.getDescription(false));
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage();

        if (errorMessageDescription == null) errorMessageDescription = ex.toString();

        BadRequestResponse response = new BadRequestResponse("", errorMessageDescription,  request.getDescription(false));
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage();

        if (errorMessageDescription == null) errorMessageDescription = ex.toString();

        InternalServiceErrorResponse response = new InternalServiceErrorResponse("", errorMessageDescription,  request.getDescription(false));
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage();
        String message = "Parametro Inválido";

        if (errorMessageDescription == null) errorMessageDescription = ex.toString();

        try {
            message = ex.getAllErrors().get(0).getDefaultMessage();
        } catch (Exception e) {
            
        }

        BadRequestResponse response = new BadRequestResponse(message, errorMessageDescription,  request.getDescription(false));
        
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage();

        if (errorMessageDescription == null) errorMessageDescription = ex.toString();

        BadRequestResponse response = new BadRequestResponse(ex.getInternalMessage(), errorMessageDescription,  request.getDescription(false));
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InternalServiceErrorException.class})
    public ResponseEntity<Object> handleInternalServiceErrorException(InternalServiceErrorException ex, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage();

        if (errorMessageDescription == null) errorMessageDescription = ex.toString();

        InternalServiceErrorResponse response = new InternalServiceErrorResponse(ex.getInternalMessage(), errorMessageDescription,  request.getDescription(false));
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}