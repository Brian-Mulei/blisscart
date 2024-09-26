package com.mulei.blisscart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mulei.blisscart.model.Order;
import com.mulei.blisscart.model.User;

public interface OrderRepository extends JpaRepository<Order,Long> {


    Optional<Order> findByid(Long id);


    List<Order> findByCustomer(User customer);


    boolean existsByReferenceNumber(String referenceNumber);


}
