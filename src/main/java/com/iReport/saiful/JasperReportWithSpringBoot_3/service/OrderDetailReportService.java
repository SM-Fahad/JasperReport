package com.iReport.saiful.JasperReportWithSpringBoot_3.service;

import com.iReport.saiful.JasperReportWithSpringBoot_3.DTOs.OrderDetailReportDto;
import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.Customer;
import com.iReport.saiful.JasperReportWithSpringBoot_3.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailReportService {

    @Autowired
    CustomerRepository customerRepository;


    public List<Customer> getAllCustomer(){
        List<Customer> csList = customerRepository.findAll();
        test();
        return csList;
    }
    public void test() {
        Customer c = customerRepository.findById(103).orElse(null);
        System.out.println(c);
    }



    private List<OrderDetailReportDto> mapToDtoList(List<Object[]> results) {
        return results.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private OrderDetailReportDto mapToDto(Object[] row) {
        return new OrderDetailReportDto(
                ((Number) row[0]).intValue(),                    // orderNumber
                ((java.sql.Date) row[1]).toLocalDate(),          // orderDate
                (String) row[2],                                 // status
                (String) row[3],                                 // customerName
                (String) row[4],                                 // productName
                ((Number) row[5]).intValue(),                    // quantityOrdered
                (Double) row[6],                             // priceEach
                (Double) row[7]                              // total
        );
    }
}