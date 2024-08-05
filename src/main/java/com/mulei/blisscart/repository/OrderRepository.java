package com.mulei.blisscart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mulei.blisscart.model.Order;
import com.mulei.blisscart.model.Product;
import com.mulei.blisscart.model.User;

public interface OrderRepository extends JpaRepository<Order,Long> {


    Optional<Order> findByid(Long id);


    Optional<Order> findByCustomer(User customer);

    

    @Query("SELECT new com.mulei.blisscart.dto.OrderDTO(p.id, p.name, o.orderTime, COUNT(oi.id)) \" +\n" + //
                "           \"FROM Product p \" +\n" + //
                "           \"JOIN OrderItem oi ON p.id = oi.product.id \" +\n" + //
                "           \"JOIN Order o ON oi.order.id = o.id \" +\n" + //
                "           \"WHERE p.vendor.id = :vendorId \" +\n" + //
                "           \"GROUP BY p.id, p.name, o.orderDate \" +\n" + //
                "           \"ORDER BY o.orderDate DESC, COUNT(oi.id) DESC")
    List<Product> findProductsSoldByVendor(@Param("vendorId") Long vendorId);}
