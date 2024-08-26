package com.mulei.blisscart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mulei.blisscart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByVendorId(Long vendorId);
    Optional<Product> findById(Long vendorId);


  // Custom query to update price

    
  //  List<Product> findByCategory(String name);
}
