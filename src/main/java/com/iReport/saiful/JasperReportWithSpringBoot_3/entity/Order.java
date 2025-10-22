package com.iReport.saiful.JasperReportWithSpringBoot_3.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @Column(name = "orderNumber")
    private Integer orderNumber;

    @Column(name = "orderDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(name = "requiredDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date requiredDate;

    @Column(name = "shippedDate")
    @Temporal(TemporalType.DATE)
    private Date shippedDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "customerNumber", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}