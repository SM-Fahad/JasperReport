package com.iReport.saiful.JasperReportWithSpringBoot_3.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employeesOld")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;
    private Double salary;
    private String email;

    // Getters and Setters
}
