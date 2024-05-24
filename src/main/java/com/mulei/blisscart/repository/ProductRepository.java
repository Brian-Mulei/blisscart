package com.mulei.blisscart.repository;

import java.util.List;
import java.util.Optional;

 import org.springframework.data.jpa.repository.JpaRepository;

import com.mulei.blisscart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByVendorId(Long vendorId);
    Optional<Product> findById(Long vendorId);
    List<Product> findByCategory(String name);
}
