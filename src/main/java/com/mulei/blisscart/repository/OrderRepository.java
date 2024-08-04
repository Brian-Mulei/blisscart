package com.mulei.blisscart.repository;

import com.mulei.blisscart.model.Order;
import com.mulei.blisscart.model.Product;
import com.mulei.blisscart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {


    Optional<Order> findByid(Long id);


    Optional<Order> findByCustomer(User customer);


    @Query("SELECT oi.product FROM OrderItem oi WHERE oi.product.vendor.id = :vendorId")
    List<Product> findProductsSoldByVendor(@Param("vendorId") Long vendorId);}
