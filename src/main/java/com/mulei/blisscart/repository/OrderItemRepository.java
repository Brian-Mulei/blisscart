package com.mulei.blisscart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

 import com.mulei.blisscart.model.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository  extends JpaRepository<OrderItem,Long>{

 @Query("SELECT oi FROM OrderItem oi JOIN oi.variation pv WHERE pv.product.vendor.id = :vendorId")
 List<OrderItem> findSoldProductsByVendor(@Param("vendorId") Long vendorId);

    
}
