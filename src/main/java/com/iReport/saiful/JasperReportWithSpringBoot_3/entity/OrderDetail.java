package com.iReport.saiful.JasperReportWithSpringBoot_3.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "orderdetails")
@IdClass(OrderDetailId.class)
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "orderNumber", nullable = false)
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "productCode", nullable = false)
    private Product product;

    @Column(name = "quantityOrdered", nullable = false)
    private Integer quantityOrdered;

    @Column(name = "priceEach", nullable = false)
    private BigDecimal priceEach;

    @Column(name = "orderLineNumber", nullable = false)
    private Short orderLineNumber;
}

