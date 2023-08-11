package com.fnunezzz.pdfmicrosservice.exception;


import java.util.Date;

public class AbstractHttpResponse {
    private String internalError;
    private int HTTP_STATUS;
    private String message;
    private String path;
    private Date timestamp;

    public AbstractHttpResponse(String internalError, String message, String path, int httpStatus) {
        this.internalError = internalError;
        this.message = message;
        this.path = path.replace("uri=", "");
        this.HTTP_STATUS = httpStatus;
        this.timestamp = new Date();
    }

    public AbstractHttpResponse(){}

    public int getHttpStatus() {
        return HTTP_STATUS;
    }

    public String getInternalError() {
        return internalError;
    }

    public void setInternalError(String internalError) {
        this.internalError = internalError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
