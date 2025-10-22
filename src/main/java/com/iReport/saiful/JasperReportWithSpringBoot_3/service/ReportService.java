package com.iReport.saiful.JasperReportWithSpringBoot_3.service;

import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.Customer;
import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.Order;
import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.Product;
import com.iReport.saiful.JasperReportWithSpringBoot_3.repository.CustomerRepository;
import com.iReport.saiful.JasperReportWithSpringBoot_3.repository.OrderDetailRepository;
import com.iReport.saiful.JasperReportWithSpringBoot_3.repository.OrderRepository;
import com.iReport.saiful.JasperReportWithSpringBoot_3.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReportService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ReportService(CustomerRepository customerRepository,
                         OrderRepository orderRepository,
                         ProductRepository productRepository,
                         OrderDetailRepository orderDetailRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
    }
//
//    // Example methods
//    public List<Customer> getCustomersByCountry(String country) {
//        return customerRepository.findByCountry(country);
//    }
//
//    public List<Order> getOrdersByCustomer(Integer customerNumber) {
//        return orderRepository.findByCustomerCustomerNumber(customerNumber);
//    }
//
//    public List<Product> getProductsByProductLine(String productLine) {
//        return productRepository.findByProductLineProductLine(productLine);
//    }
//
//    public BigDecimal getOrderTotal(Integer orderNumber) {
//        return orderDetailRepository.calculateOrderTotal(orderNumber);
//    }
}