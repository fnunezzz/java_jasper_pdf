package com.fnunezzz.pdfmicrosservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fnunezzz.pdfmicrosservice.AppConfig;
import com.fnunezzz.pdfmicrosservice.dto.ResponseDto;
import com.fnunezzz.pdfmicrosservice.dto.TemplateDataDto;
import com.fnunezzz.pdfmicrosservice.service.PdfService;

@RestController
@RequestMapping(AppConfig.BASE_PATH + "/pdf")
public class PdfController {
    
    @Autowired
    private PdfService pdfService;

    @RequestMapping(method = RequestMethod.POST, value = "/{template-name}")
    public ResponseEntity<ResponseDto> gerar(@Valid @RequestBody TemplateDataDto body, @PathVariable("template-name") String templateName) throws Exception {
        return new ResponseEntity<ResponseDto>(pdfService.buildBase64(body, templateName), HttpStatus.OK);
    }

}
