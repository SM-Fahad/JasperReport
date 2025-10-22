//package com.iReport.saiful.JasperReportWithSpringBoot_3.service;
//
//
//
//
//import com.iReport.saiful.JasperReportWithSpringBoot_3.DTOs.OrderDetailReportDto;
//import lombok.RequiredArgsConstructor;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
//import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
//import net.sf.jasperreports.export.*;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class OrderDetailJasperService {
//
//    private final OrderDetailReportService orderDetailReportService;
//
//    public byte[] generateOrderDetailReport(String format, Map<String, Object> parameters) throws JRException {
//        List<OrderDetailReportDto> orderDetails = orderDetailReportService.getAllOrderDetails();
//
//        // Load your specific JRXML file
//        InputStream reportStream = getClass().getResourceAsStream("/reports/Blank_A4_1.jrxml");
//        if (reportStream == null) {
//            throw new JRException("Report template not found: Blank_A4_1.jrxml");
//        }
//
//        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
//
//        // Create data source
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orderDetails);
//
//        // Fill report with parameters
//        if (parameters == null) {
//            parameters = new HashMap<>();
//        }
//        parameters.put("ReportTitle", "Order Details Report - Grouped by Customer");
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//
//        // Export to requested format
//        return exportReport(jasperPrint, format);
//    }
//
//    public byte[] generateOrderDetailReportByCustomer(String customerName, String format, Map<String, Object> parameters) throws JRException {
//        List<OrderDetailReportDto> orderDetails = orderDetailReportService.getOrderDetailsByCustomerName(customerName);
//
//        InputStream reportStream = getClass().getResourceAsStream("/reports/Blank_A4_1.jrxml");
//        if (reportStream == null) {
//            throw new JRException("Report template not found: Blank_A4_1.jrxml");
//        }
//
//        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orderDetails);
//
//        if (parameters == null) {
//            parameters = new HashMap<>();
//        }
//        parameters.put("ReportTitle", "Order Details Report - Customer: " + customerName);
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//
//        return exportReport(jasperPrint, format);
//    }
//
//    private byte[] exportReport(JasperPrint jasperPrint, String format) throws JRException {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//        switch (format.toLowerCase()) {
//            case "pdf":
//                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//                break;
//
//            case "xlsx":
//                JRXlsxExporter excelExporter = new JRXlsxExporter();
//                excelExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//                excelExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
//
//                SimpleXlsxReportConfiguration excelConfig = new SimpleXlsxReportConfiguration();
//                excelConfig.setOnePagePerSheet(false);
//                excelConfig.setRemoveEmptySpaceBetweenRows(true);
//                excelConfig.setDetectCellType(true);
//                excelConfig.setWhitePageBackground(false);
//                excelExporter.setConfiguration(excelConfig);
//
//                excelExporter.exportReport();
//                break;
//
//            case "docx":
//                JRDocxExporter wordExporter = new JRDocxExporter();
//                wordExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//                wordExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
//                wordExporter.exportReport();
//                break;
//
//            case "html":
//                // For HTML export, we need to handle it differently
//                JasperExportManager.exportReportToHtmlFile(jasperPrint, "order_details_report.html");
//                // In production, you might want to use JRHtmlExporter for better control
//                break;
//
//            default:
//                throw new IllegalArgumentException("Unsupported format: " + format);
//        }
//
//        return outputStream.toByteArray();
//    }
//
//    // Method to get report parameters with default values
//    public Map<String, Object> getDefaultReportParameters() {
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("ReportTitle", "Order Details Report");
//        parameters.put("GeneratedBy", "ClassicModels Reporting System");
//        parameters.put("CompanyName", "Classic Models Inc.");
//        return parameters;
//    }
//}