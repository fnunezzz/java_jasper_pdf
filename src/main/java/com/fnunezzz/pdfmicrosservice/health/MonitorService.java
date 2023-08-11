package com.fnunezzz.pdfmicrosservice.health;
import org.springframework.stereotype.Service;

import com.fnunezzz.pdfmicrosservice.dto.HealthCheckDto;

import java.util.Date;

@Service
public class MonitorService {

    private Boolean active;

    // it's useless but it can be used for a database connection verification
    public void verifyState() {
            this.active = Boolean.TRUE;
    }

    public HealthCheckDto getState() {
        this.verifyState();
        HealthCheckDto healthCheckDto = new HealthCheckDto();
        healthCheckDto.setActive(this.active);
        healthCheckDto.setDataHora(new Date());
        return healthCheckDto;
    }

}