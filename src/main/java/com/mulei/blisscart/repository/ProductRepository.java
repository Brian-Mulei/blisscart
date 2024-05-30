package com.mulei.blisscart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mulei.blisscart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByVendorId(Long vendorId);
    Optional<Product> findById(Long vendorId);


  // Custom query to update price
    @Modifying
    @Query("UPDATE Product p SET p.price = :price WHERE p.id = :productId")
    void updatePrice(@Param("productId") Long productId, @Param("price") Double price);

    // Custom query to update quantity
    @Modifying
    @Query("UPDATE Product p SET p.quantity = :quantity WHERE p.id = :productId")
    void updateQuantity(@Param("productId") Long productId, @Param("quantity") Integer quantity);

    
  //  List<Product> findByCategory(String name);
}
