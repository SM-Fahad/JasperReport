package com.iReport.saiful.JasperReportWithSpringBoot_3.repository;

import com.iReport.saiful.JasperReportWithSpringBoot_3.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
//
//    // Find products by product line
//    List<Product> findByProductLineProductLine(String productLine);
//
//    // Find products by product name containing (like search)
//    List<Product> findByProductNameContaining(String productName);
//
//    // Find products by product vendor
//    List<Product> findByProductVendor(String productVendor);
//
//    // Find products with quantity in stock less than specified value
//    List<Product> findByQuantityInStockLessThan(Integer quantity);
//
//    // Find products with buy price between range
//    List<Product> findByBuyPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
//
//    // Find products by product scale
//    List<Product> findByProductScale(String productScale);
//
//    // Find product by exact name
//    Optional<Product> findByProductName(String productName);
//
//    // Find products with MSRP greater than buy price
//    @Query("SELECT p FROM Product p WHERE p.msrp > p.buyPrice")
//    List<Product> findProductsWithProfitMargin();
//
//    // Find products with their product line details
//    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.productLine WHERE p.productCode = :productCode")
//    Optional<Product> findByIdWithProductLine(@Param("productCode") String productCode);
//
//
//    // Find products that have never been ordered
//    @Query("SELECT p FROM Product p WHERE p.orderDetails IS EMPTY")
//    List<Product> findProductsNeverOrdered();
//
//    // Find products with low stock (less than specified quantity)
//    @Query("SELECT p FROM Product p WHERE p.quantityInStock < :threshold")
//    List<Product> findLowStockProducts(@Param("threshold") Integer threshold);
//
//    // Calculate total inventory value
//    @Query("SELECT SUM(p.quantityInStock * p.buyPrice) FROM Product p")
//    BigDecimal calculateTotalInventoryValue();
//
//    // Find products by multiple criteria
//    @Query("SELECT p FROM Product p WHERE " +
//            "(:productLine IS NULL OR p.productLine.productLine = :productLine) AND " +
//            "(:minPrice IS NULL OR p.buyPrice >= :minPrice) AND " +
//            "(:maxPrice IS NULL OR p.buyPrice <= :maxPrice)")
//    List<Product> findByCriteria(@Param("productLine") String productLine,
//                                 @Param("minPrice") BigDecimal minPrice,
//                                 @Param("maxPrice") BigDecimal maxPrice);
}
