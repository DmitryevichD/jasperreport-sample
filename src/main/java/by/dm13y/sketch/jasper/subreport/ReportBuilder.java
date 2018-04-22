package by.dm13y.sketch.jasper.subreport;

import by.dm13y.sketch.jasper.subreport.reportBeans.GeneralReportBean;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportBuilder {
    private final String reportFolder = "reports/subreports/";
    private final String masterReportForm = "general-report.jrxml";
    private final String subReportForm = "sub-report.jrxml";
    private final String pdfFileName = "report.pdf";

    public void build() {

        List<GeneralReportBean> reportBeans = ReportHelper.genReportBeans(100);

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(reportBeans);

        try {

            JasperReport subReport = JasperCompileManager.compileReport(Paths.get(reportFolder + subReportForm).toString());
            JasperReport masterReport = JasperCompileManager.compileReport(Paths.get(reportFolder + masterReportForm).toString());


            Map<String, Object> parameters = new HashMap<>();
            parameters.put("subReport", subReport);
            parameters.put("customClass", new CustomClass());

   //         JasperFillManager.fillReportToFile(masterReport, printFile, parameters, beanCollectionDataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parameters, beanCollectionDataSource);

            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ReportBuilder().build();
    }
}
