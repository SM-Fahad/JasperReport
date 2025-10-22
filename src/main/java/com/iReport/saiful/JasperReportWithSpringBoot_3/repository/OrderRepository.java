package com.iReport.saiful.JasperReportWithSpringBoot_3.repository;

import com.iReport.saiful.JasperReportWithSpringBoot_3.DTOs.OrderDetailReportDto;
import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Find orders by customer number
    List<Order> findByCustomerCustomerNumber(Integer customerNumber);

    // Find orders by status
    List<Order> findByStatus(String status);

    // Find orders between dates
    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);

    // Find orders by order date
    List<Order> findByOrderDate(LocalDate orderDate);

    // Find orders that are shipped after required date
    @Query("SELECT o FROM Order o WHERE o.shippedDate > o.requiredDate")
    List<Order> findDelayedOrders();

    // Find orders with customer details
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.customer WHERE o.orderNumber = :orderNumber")
    Optional<Order> findByIdWithCustomer(@Param("orderNumber") Integer orderNumber);

    // Find orders with order details and products
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderDetails od LEFT JOIN FETCH od.product WHERE o.orderNumber = :orderNumber")
    Optional<Order> findByIdWithDetailsAndProducts(@Param("orderNumber") Integer orderNumber);

    // Find orders by customer country
    @Query("SELECT o FROM Order o WHERE o.customer.country = :country")
    List<Order> findByCustomerCountry(@Param("country") String country);

    // Count orders by status
    @Query("SELECT o.status, COUNT(o) FROM Order o GROUP BY o.status")
    List<Object[]> countOrdersByStatus();

    // Find total sales by month
    @Query("SELECT YEAR(o.orderDate), MONTH(o.orderDate), SUM(od.quantityOrdered * od.priceEach) " +
            "FROM Order o JOIN o.orderDetails od " +
            "GROUP BY YEAR(o.orderDate), MONTH(o.orderDate) " +
            "ORDER BY YEAR(o.orderDate), MONTH(o.orderDate)")
    List<Object[]> findMonthlySales();
//    @Query("""
//        SELECT new com.iReport.saiful.JasperReportWithSpringBoot_3.DTOs.OrderDetailReportDto(
//            o.orderNumber,
//            o.orderDate,
//            o.status,
//            c.customerName,
//            p.productName,
//            od.quantityOrdered,
//            od.priceEach,
//            (od.quantityOrdered * od.priceEach)
//        )
//        FROM Order o
//        JOIN o.customer c
//        JOIN o.orderDetails od
//        JOIN od.product p
//        """)
//    List<OrderDetailReportDto> findOrderDetails();
}
