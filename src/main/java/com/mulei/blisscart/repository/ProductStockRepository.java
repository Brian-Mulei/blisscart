package com.mulei.blisscart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mulei.blisscart.model.ProductStock;

public interface ProductStockRepository extends JpaRepository<ProductStock, Integer> {

}