package com.iReport.saiful.JasperReportWithSpringBoot_3.repository;

import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Find customer by name (exact match)
    Optional<Customer> findByCustomerName(String customerName);

    // Find customers by country
    List<Customer> findByCountry(String country);

    // Find customers by city
    List<Customer> findByCity(String city);
//
//    // Find customers by sales representative employee number
//    List<Customer> findBySalesRepEmployeeNumber(Integer salesRepEmployeeNumber);
//
//    // Find customers with credit limit greater than specified value
//    List<Customer> findByCreditLimitGreaterThan(Double creditLimit);
//
//    // Custom query to find customers by country and city
//    @Query("SELECT c FROM Customer c WHERE c.country = :country AND c.city = :city")
//    List<Customer> findByCountryAndCity(@Param("country") String country, @Param("city") String city);
//
//    // Custom query to find customers with their sales rep information
//    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.salesRep WHERE c.customerNumber = :customerNumber")
//    Optional<Customer> findByIdWithSalesRep(@Param("customerNumber") Integer customerNumber);
//
//    // Find all customers with their orders (eager loading)
//    @Query("SELECT DISTINCT c FROM Customer c LEFT JOIN FETCH c.orders")
//    List<Customer> findAllWithOrders();
//
//
//
//
//
//    // Count customers by country
//    @Query("SELECT c.country, COUNT(c) FROM Customer c GROUP BY c.country")
//    List<Object[]> countCustomersByCountry();

//
//    // Count customers by country
//    @Query(value = "SELECT \n" +
//            "    o.orderNumber,\n" +
//            "    o.orderDate,\n" +
//            "    o.status,\n" +
//            "    c.customerName,\n" +
//            "    p.productName,\n" +
//            "    od.quantityOrdered,\n" +
//            "    od.priceEach,\n" +
//            "    (od.quantityOrdered * od.priceEach) AS total\n" +
//            "FROM orderdetails od\n" +
//            "JOIN orders o \n" +
//            "    ON od.orderNumber = o.orderNumber\n" +
//            "JOIN customers c \n" +
//            "    ON o.customerNumber = c.customerNumber\n" +
//            "JOIN products p \n" +
//            "    ON od.productCode = p.productCode\n" +
//            "ORDER BY o.orderNumber", nativeQuery = true)
//    List<Object[]> getReportData();


}