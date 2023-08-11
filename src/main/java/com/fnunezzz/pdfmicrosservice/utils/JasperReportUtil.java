package com.fnunezzz.pdfmicrosservice.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;

public final class JasperReportUtil {

    public static final String REPORT_REGISTRATION_FORM = "registration-form";

    private String JASPER_PATH = System.getenv("JASPER_PATH");
    private final Map<String, Object> JASPER_PARAMS = new HashMap<>();
    private static JasperReportUtil instance;

    private JasperReportUtil() throws Exception {
        if (System.getenv("JASPER_PATH") == null) {
            throw new Exception("JASPER_PATH não informado");
        }

        if (System.getenv("JASPER_PATH").trim().equals("")) {
            throw new Exception("JASPER_PATH não informado");
        }

        this.JASPER_PATH = System.getenv("JASPER_PATH") + "/";

        this.JASPER_PARAMS.put(JsonQueryExecuterFactory.JSON_DATE_PATTERN, "dd-MM-yyyy");
		this.JASPER_PARAMS.put(JsonQueryExecuterFactory.JSON_NUMBER_PATTERN, "#,##0.00");
    }

    public static JasperReportUtil getInstance() throws Exception {
        if (instance == null) {
            instance = new JasperReportUtil();
        }
        return instance;
    }

   /**
     * Builds a base64 PDF
     * @param fileName template name
     * @param params A JSON string mapped to Jasper fields
     * @return String
     * @throws Exception
   */
    public String buildBase64(final String fileName, final String params) throws Exception {
            JasperPrint jasperPrint = this.getJasperPrint(fileName, params);
            byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

            String pdfBase64 = Base64.getEncoder().encodeToString(pdf);
            return pdfBase64;
    }  

    /**
     * Builds the Jasper Print
     * @param fileName template name
     * @param params A JSON string mapped to Jasper fields
     * @return A JasperPrint
     * @throws Exception
    */
    private JasperPrint getJasperPrint(final String fileName, final String params) throws Exception {
        InputStream jasperStream = new FileInputStream(this.JASPER_PATH + fileName + ".jasper");
        JsonDataSource dataSource = null;
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            dataSource = new JsonDataSource(new ByteArrayInputStream(params.getBytes("UTF-8")), fileName);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, this.JASPER_PARAMS, dataSource);
            return jasperPrint;
        } finally {
            jasperStream.close();
        }	

    }
}
