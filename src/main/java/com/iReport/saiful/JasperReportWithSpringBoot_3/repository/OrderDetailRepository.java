package com.iReport.saiful.JasperReportWithSpringBoot_3.repository;


import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.OrderDetail;
import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

    // Find order details by order number
    List<OrderDetail> findByOrderOrderNumber(Integer orderNumber);

    // Find order details by product code
    List<OrderDetail> findByProductProductCode(String productCode);

    // Find order details with quantity greater than specified value
    List<OrderDetail> findByQuantityOrderedGreaterThan(Integer quantity);

    // Find order details with price each greater than specified value
    List<OrderDetail> findByPriceEachGreaterThan(BigDecimal price);

    // Find order details with order and product information
    @Query("SELECT od FROM OrderDetail od LEFT JOIN FETCH od.order LEFT JOIN FETCH od.product WHERE od.order.orderNumber = :orderNumber")
    List<OrderDetail> findByOrderNumberWithDetails(@Param("orderNumber") Integer orderNumber);

    // Calculate total amount for an order
    @Query("SELECT SUM(od.quantityOrdered * od.priceEach) FROM OrderDetail od WHERE od.order.orderNumber = :orderNumber")
    BigDecimal calculateOrderTotal(@Param("orderNumber") Integer orderNumber);

    // Find best selling products
    @Query("SELECT od.product.productCode, od.product.productName, SUM(od.quantityOrdered) as totalQuantity " +
            "FROM OrderDetail od " +
            "GROUP BY od.product.productCode, od.product.productName " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findBestSellingProducts();

    // Find order details for a specific product in a date range
    @Query("SELECT od FROM OrderDetail od WHERE od.product.productCode = :productCode AND od.order.orderDate BETWEEN :startDate AND :endDate")
    List<OrderDetail> findByProductAndDateRange(@Param("productCode") String productCode,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);

    // Find average order value by product
    @Query("SELECT p.productCode, p.productName, AVG(od.quantityOrdered * od.priceEach) as avgOrderValue " +
            "FROM OrderDetail od JOIN od.product p " +
            "GROUP BY p.productCode, p.productName")
    List<Object[]> findAverageOrderValueByProduct();
}