package com.fnunezzz.pdfmicrosservice.exception;

public class InternalServiceErrorException extends AbstractRuntimeException {

    public InternalServiceErrorException(String error) {
        super(error, "Internal Service Error");
    }

}