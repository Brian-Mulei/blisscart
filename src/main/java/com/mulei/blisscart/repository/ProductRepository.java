package com.mulei.blisscart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mulei.blisscart.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByVendorId(Long vendorId);
    Optional<Product> findById(Long vendorId);

    @Query("SELECT p FROM Product p WHERE p.vendor.id = :vendorId")
    List<Product> findProductsByVendor(@Param("vendorId") Long vendorId);

  // Custom query to update price

    
  //  List<Product> findByCategory(String name);
}
