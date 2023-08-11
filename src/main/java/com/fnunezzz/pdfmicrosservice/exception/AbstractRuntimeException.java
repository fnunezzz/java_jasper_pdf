package com.fnunezzz.pdfmicrosservice.exception;


public class AbstractRuntimeException extends RuntimeException {

    private String INTERNAL_MESSAGE;

    public AbstractRuntimeException(String error, String internalMessage) {
        super(error);
        this.INTERNAL_MESSAGE = internalMessage;
    }

    public String getInternalMessage(){
        return this.INTERNAL_MESSAGE;
    }

}