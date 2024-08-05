package com.mulei.blisscart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

 import com.mulei.blisscart.model.OrderItem;

public interface OrderItemRepository  extends JpaRepository<OrderItem,Long>{


    
}
