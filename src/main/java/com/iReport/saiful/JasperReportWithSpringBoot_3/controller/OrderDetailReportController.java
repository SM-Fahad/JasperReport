//package com.iReport.saiful.JasperReportWithSpringBoot_3.controller;
//
//
//import com.iReport.saiful.JasperReportWithSpringBoot_3.DTOs.OrderDetailReportDto;
//import com.iReport.saiful.JasperReportWithSpringBoot_3.service.OrderDetailJasperService;
//import com.iReport.saiful.JasperReportWithSpringBoot_3.service.OrderDetailReportService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/reports/order-details")
//@RequiredArgsConstructor
//public class OrderDetailReportController {
//
//    private final OrderDetailReportService orderDetailReportService;
//    private final OrderDetailJasperService orderDetailJasperService;
//
//    // REST API endpoints for data
//    @GetMapping("/data")
//    public ResponseEntity<List<OrderDetailReportDto>> getAllOrderDetails() {
//        List<OrderDetailReportDto> orderDetails = orderDetailReportService.getAllOrderDetails();
//        return ResponseEntity.ok(orderDetails);
//    }
//
//    @GetMapping("/data/customer/{customerName}")
//    public ResponseEntity<List<OrderDetailReportDto>> getOrderDetailsByCustomerName(
//            @PathVariable String customerName) {
//        List<OrderDetailReportDto> orderDetails = orderDetailReportService.getOrderDetailsByCustomerName(customerName);
//        return ResponseEntity.ok(orderDetails);
//    }
//
//    @GetMapping("/data/order/{orderNumber}")
//    public ResponseEntity<List<OrderDetailReportDto>> getOrderDetailsByOrderNumber(
//            @PathVariable Integer orderNumber) {
//        List<OrderDetailReportDto> orderDetails = orderDetailReportService.getOrderDetailsByOrderNumber(orderNumber);
//        return ResponseEntity.ok(orderDetails);
//    }
//
//    // JasperReports export endpoints
//    @GetMapping("/export")
//    public ResponseEntity<byte[]> exportOrderDetailReport(
//            @RequestParam String format,
//            @RequestParam(required = false) String customerName,
//            @RequestParam(required = false) String reportTitle) {
//
//        try {
//            Map<String, Object> parameters = orderDetailJasperService.getDefaultReportParameters();
//
//            if (reportTitle != null) {
//                parameters.put("ReportTitle", reportTitle);
//            }
//
//            byte[] reportBytes;
//
//            if (customerName != null && !customerName.trim().isEmpty()) {
//                reportBytes = orderDetailJasperService.generateOrderDetailReportByCustomer(customerName, format, parameters);
//            } else {
//                reportBytes = orderDetailJasperService.generateOrderDetailReport(format, parameters);
//            }
//
//            String contentType = getContentType(format);
//            String filename = "order_details_report" +
//                    (customerName != null ? "_" + customerName.replaceAll("\\s+", "_") : "") +
//                    "." + format;
//
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
//                    .contentType(MediaType.parseMediaType(contentType))
//                    .body(reportBytes);
//
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body(("Error generating report: " + e.getMessage()).getBytes());
//        }
//    }
//
//    @GetMapping("/export/advanced")
//    public ResponseEntity<byte[]> exportAdvancedOrderDetailReport(
//            @RequestParam String format,
//            @RequestParam(required = false) String customerName,
//            @RequestParam(required = false) String status,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
//            @RequestParam(required = false) String reportTitle) {
//
//        try {
//            // For advanced filtering, you might need to modify the service to handle multiple filters
//            // For now, we'll use customer name as the primary filter
//            Map<String, Object> parameters = orderDetailJasperService.getDefaultReportParameters();
//
//            if (reportTitle != null) {
//                parameters.put("ReportTitle", reportTitle);
//            }
//
//            // Add additional parameters based on filters
//            if (status != null) {
//                parameters.put("StatusFilter", status);
//            }
//            if (startDate != null && endDate != null) {
//                parameters.put("DateRange", startDate + " to " + endDate);
//            }
//
//            byte[] reportBytes;
//
//            if (customerName != null && !customerName.trim().isEmpty()) {
//                reportBytes = orderDetailJasperService.generateOrderDetailReportByCustomer(customerName, format, parameters);
//            } else {
//                reportBytes = orderDetailJasperService.generateOrderDetailReport(format, parameters);
//            }
//
//            String contentType = getContentType(format);
//            String filename = "order_details_advanced" +
//                    (customerName != null ? "_" + customerName.replaceAll("\\s+", "_") : "") +
//                    "." + format;
//
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
//                    .contentType(MediaType.parseMediaType(contentType))
//                    .body(reportBytes);
//
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body(("Error generating report: " + e.getMessage()).getBytes());
//        }
//    }
//
//    private String getContentType(String format) {
//        return switch (format.toLowerCase()) {
//            case "pdf" -> "application/pdf";
//            case "xlsx" -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//            case "docx" -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
//            case "html" -> "text/html";
//            default -> "application/octet-stream";
//        };
//    }
//}