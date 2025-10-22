package com.iReport.saiful.JasperReportWithSpringBoot_3.service;


import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.Employee;
import com.iReport.saiful.JasperReportWithSpringBoot_3.repository.EmployeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReportServiceOld {
    private final EmployeeRepository repo;
    private final Map<String, JasperReport> compiledReports = new ConcurrentHashMap<>();

    public ReportServiceOld(EmployeeRepository repo) {
        this.repo = repo;
    }

    public byte[] generateReport(String reportType, String format, Map<String, Object> params) throws Exception {
        List<Employee> data = getReportData(reportType, params);

        JasperReport jasperReport = compileReport(reportType);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, params, dataSource);

        return exportReport(print, format);
    }

    private List<Employee> getReportData(String reportType, Map<String, Object> params) {
        switch (reportType.toLowerCase()) {
            case "employee_list":
                return repo.findAll();
            case "department_summary":
                return repo.findAll(); // Consider adding department filtering
            case "employee_detail":
                Long empId = Long.parseLong(params.get("employeeId").toString());
                return repo.findById(empId).stream().toList();
            default:
                throw new IllegalArgumentException("Invalid report type: " + reportType);
        }
    }

    private JasperReport compileReport(String reportType) throws JRException {
        return compiledReports.computeIfAbsent(reportType, key -> {
            try {
                InputStream stream = getClass().getResourceAsStream("/reports/" + reportType + ".jrxml");
                if (stream == null) {
                    throw new RuntimeException("Report template not found: " + reportType);
                }
                return JasperCompileManager.compileReport(stream);
            } catch (JRException e) {
                throw new RuntimeException("Failed to compile report: " + reportType, e);
            }
        });
    }
    private byte[] exportReport(JasperPrint print, String format) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        switch (format.toLowerCase()) {
            case "pdf" -> JasperExportManager.exportReportToPdfStream(print, out);
            case "html" -> {
                // Export to HTML file and read it back
                String fileName = "temp_report_" + System.currentTimeMillis() + ".html";
                JasperExportManager.exportReportToHtmlFile(print, fileName);

                // Read the generated HTML file
                File htmlFile = new File(fileName);
                byte[] htmlBytes = Files.readAllBytes(htmlFile.toPath());
                out.write(htmlBytes);

                // Delete the temporary file
                htmlFile.delete();
            }
            case "xlsx" -> {
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(print));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                exporter.exportReport();
            }
            case "docx" -> {
                JRDocxExporter exporter = new JRDocxExporter();
                exporter.setExporterInput(new SimpleExporterInput(print));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                exporter.exportReport();
            }
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        }
        return out.toByteArray();
    }
    private static JRXlsxExporter getJrXlsxExporter(JasperPrint print, ByteArrayOutputStream out) {
        JRXlsxExporter exporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
        config.setOnePagePerSheet(false);
        config.setRemoveEmptySpaceBetweenRows(true);
        config.setDetectCellType(true);
        exporter.setConfiguration(config);
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        return exporter;
    }
}
//
//@Service
//public class ReportServiceOld {
//    private final EmployeeRepository repo;
//
//    public ReportServiceOld(EmployeeRepository repo) {
//        this.repo = repo;
//    }
//
//    public byte[] generateReport(String reportType, String format, Map<String, Object> params) throws Exception {
//        List<Employee> data;
//
//        switch (reportType.toLowerCase()) {
//            case "employee_list":
//                data = repo.findAll();
//                break;
//            case "department_summary":
//                data = repo.findAll();
//                break;
//            case "employee_detail":
//                Long empId = Long.parseLong(params.get("employeeId").toString());
//                data = repo.findById(empId).stream().toList();
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid report type: " + reportType);
//        }
//
//        InputStream stream = getClass().getResourceAsStream("/reports/" + reportType + ".jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(stream);
//
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
//        JasperPrint print = JasperFillManager.fillReport(jasperReport, params, dataSource);
//
//        return exportReport(print, format);
//    }
//
//    private byte[] exportReport(JasperPrint print, String format) throws Exception {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        switch (format.toLowerCase()) {
//            case "pdf" -> JasperExportManager.exportReportToPdfStream(print, out);
////            case "html" -> JasperExportManager.exportReportToHtmlFile(print, "employee.html");
//            case "html" -> {
//                JasperExportManager.exportReportToHtmlFile(print, "employee.html");
//                // Read the file back or use a different approach
//                File htmlFile = new File("employee.html");
//                out.write(Files.readAllBytes(htmlFile.toPath()));
//                htmlFile.delete(); // Clean up
//            }
//
//            case "xlsx" -> {
//                JRXlsxExporter exporter = new JRXlsxExporter();
//                exporter.setExporterInput(new SimpleExporterInput(print));
//                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
//                exporter.exportReport();
//            }
//            case "docx" -> {
//                JRDocxExporter exporter = new JRDocxExporter();
//                exporter.setExporterInput(new SimpleExporterInput(print));
//                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
//                exporter.exportReport();
//            }
//            default -> throw new IllegalArgumentException("Unknown format: " + format);
//        }
//        return out.toByteArray();
//    }
//}
