package com.iReport.saiful.JasperReportWithSpringBoot_3.DTOs;


import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderDetailReportDto {
    private Integer orderNumber;
    private LocalDate orderDate;
    private String status;
    private String customerName;
    private String productName;
    private Integer quantityOrdered;
    private Double priceEach;
    private Double total;

    public OrderDetailReportDto(Integer orderNumber, LocalDate orderDate, String status,
                                String customerName, String productName,
                                Integer quantityOrdered, Double priceEach, Double total) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.status = status;
        this.customerName = customerName;
        this.productName = productName;
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
        this.total = total;
    }

    // Getters (no setters needed for report)
}
