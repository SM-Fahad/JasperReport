//package com.iReport.saiful.JasperReportWithSpringBoot_3.DTOs;
//
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//
//@Entity
//@SqlResultSetMapping(
//        name = "OrderDetailReportDtoMapping",
//        classes = @ConstructorResult(
//                targetClass = OrderDetailReportDto.class,
//                columns = {
//                        @ColumnResult(name = "orderNumber", type = Integer.class),
//                        @ColumnResult(name = "orderDate", type = java.sql.Date.class),
//                        @ColumnResult(name = "status", type = String.class),
//                        @ColumnResult(name = "customerName", type = String.class),
//                        @ColumnResult(name = "productName", type = String.class),
//                        @ColumnResult(name = "quantityOrdered", type = Integer.class),
//                        @ColumnResult(name = "priceEach", type = BigDecimal.class),
//                        @ColumnResult(name = "total", type = BigDecimal.class)
//                }
//        )
//)
//public class MappingConfig {
//    @Id
//    private Long id;
//}
