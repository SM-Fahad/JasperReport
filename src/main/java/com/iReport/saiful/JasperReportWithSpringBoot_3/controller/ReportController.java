package com.iReport.saiful.JasperReportWithSpringBoot_3.controller;


import com.iReport.saiful.JasperReportWithSpringBoot_3.service.ReportServiceOld;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportServiceOld service;

    public ReportController(ReportServiceOld service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<byte[]> getReport(
            @RequestParam String type,
            @RequestParam String format,
            @RequestParam(required = false) Long employeeId
    ) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("createdBy", "Saiful The Boss 😎");
        if (employeeId != null) params.put("employeeId", employeeId);

        byte[] data = service.generateReport(type, format, params);

        String contentType = switch (format) {
            case "pdf" -> "application/pdf";
            case "html" -> "text/html";
            case "xlsx" -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case "docx" -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            default -> "application/octet-stream";
        };

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + type + "." + format)
                .contentType(MediaType.parseMediaType(contentType))
                .body(data);
    }
}
