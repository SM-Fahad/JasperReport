package com.iReport.saiful.JasperReportWithSpringBoot_3.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    private Integer customerNumber;

    private String customerName;

    private String contactLastName;

    private String contactFirstName;

    private String phone;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    private Integer salesRepEmployeeNumber;

    private BigDecimal creditLimit;

    @OneToMany
    @JoinColumn(name = "customerNumber")
    private List<Order> orders;

}