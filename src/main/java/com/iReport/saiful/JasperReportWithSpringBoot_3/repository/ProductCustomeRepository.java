package com.iReport.saiful.JasperReportWithSpringBoot_3.repository;

import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.Product;
import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.ProductCustome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCustomeRepository  extends JpaRepository<ProductCustome, Integer> {

    public List<ProductCustome> findByName(String name);

}
