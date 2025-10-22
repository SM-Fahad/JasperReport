package com.iReport.saiful.JasperReportWithSpringBoot_3.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @Column(name = "productCode")
    private String productCode;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productLine", nullable = false)
    private String productLine;

    @Column(name = "productScale", nullable = false)
    private String productScale;

    @Column(name = "productVendor", nullable = false)
    private String productVendor;

    @Column(name = "productDescription", nullable = false)
    private String productDescription;

    @Column(name = "quantityInStock", nullable = false)
    private Short quantityInStock;

    @Column(name = "buyPrice", nullable = false)
    private BigDecimal buyPrice;

    @Column(name = "MSRP", nullable = false)
    private BigDecimal msrp;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}