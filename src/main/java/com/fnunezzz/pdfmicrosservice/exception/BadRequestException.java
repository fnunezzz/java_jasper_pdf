package com.fnunezzz.pdfmicrosservice.exception;

public class BadRequestException extends AbstractRuntimeException {

    public BadRequestException(String error) {
        super(error, "Bad Request By Client");
    }


}