package com.fnunezzz.pdfmicrosservice.exception;

import org.springframework.http.HttpStatus;


public class InternalServiceErrorResponse extends AbstractHttpResponse {
    public InternalServiceErrorResponse(String message, String error, String path) {
        super(message, error, path, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
