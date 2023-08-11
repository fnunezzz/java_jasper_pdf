package com.fnunezzz.pdfmicrosservice.exception;

import org.springframework.http.HttpStatus;


public class HttpMethodNotSupportedResponse extends AbstractHttpResponse {
    public HttpMethodNotSupportedResponse(String message, String error, String path) {
        super(message, error, path, HttpStatus.METHOD_NOT_ALLOWED.value());
    }

}
