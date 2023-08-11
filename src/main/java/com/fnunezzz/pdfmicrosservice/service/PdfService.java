package com.fnunezzz.pdfmicrosservice.service;

import org.springframework.stereotype.Service;

import com.fnunezzz.pdfmicrosservice.classes.registrationForm.RegistrationForm;
import com.fnunezzz.pdfmicrosservice.dto.TemplateDataDto;
import com.fnunezzz.pdfmicrosservice.dto.ResponseDto;
import com.fnunezzz.pdfmicrosservice.exception.InternalServiceErrorException;
import com.fnunezzz.pdfmicrosservice.utils.JasperReportUtil;
import com.fnunezzz.pdfmicrosservice.utils.JsonUtils;

@Service
public class PdfService {

    public ResponseDto buildBase64(TemplateDataDto body, String templateName) throws Exception {
        String json;
        switch (templateName) {
            case JasperReportUtil.REPORT_REGISTRATION_FORM:
                RegistrationForm form = JsonUtils.deserialize(RegistrationForm.class, body.getData());
                json = form.buildJsonObject(JasperReportUtil.REPORT_REGISTRATION_FORM);
                break;
            default:
                throw new InternalServiceErrorException("Print " + templateName + " not found");
        }
        JasperReportUtil jasperReportUtil = JasperReportUtil.getInstance();
        String pdf = jasperReportUtil.buildBase64(templateName, json);
        ResponseDto dto = new ResponseDto();
        dto.setPdf(pdf);

        return dto;
    }

}
