package com.fnunezzz.pdfmicrosservice.dto;


public class ResponseDto {
    private String pdf;

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = "data:application/pdf;base64," + pdf;
    }

}
