package com.mulei.blisscart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mulei.blisscart.model.ProductVariation;

public interface ProductVariationRepository  extends JpaRepository<ProductVariation, Long>{

    @Modifying
    @Query("UPDATE ProductVariation p SET p.price = :price WHERE p.id = :productVariationId")
    void updatePrice(@Param("productVariationId") Long productVariationId, @Param("price") Double price);

    // Custom query to update quantity
    @Modifying
    @Query("UPDATE ProductVariation p SET p.quantity = :quantity WHERE p.id = :productVariationId")
    void updateQuantity(@Param("productVariationId") Long productVariationId, @Param("quantity") Integer quantity);

    @Modifying
    @Query("DELETE ProductVariation p WHERE p.id = :productVariationId")
    void deleteVariation(@Param("productVariationId") Long productVariationId );

}
