package com.fnunezzz.pdfmicrosservice.dto;

import java.util.Date;

public class HealthCheckDto {

    private Boolean active;
    private Date dataHora;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
}
