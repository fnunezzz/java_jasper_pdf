package com.fnunezzz.pdfmicrosservice.exception;

import org.springframework.http.HttpStatus;


public class BadRequestResponse extends AbstractHttpResponse {

    public BadRequestResponse(String message, String error, String path) {
        super(message, error, path, HttpStatus.BAD_REQUEST.value());
    }

}
