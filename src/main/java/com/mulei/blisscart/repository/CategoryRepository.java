package com.mulei.blisscart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mulei.blisscart.model.Category; 

public interface CategoryRepository extends JpaRepository<Category, Long> {

     
 
   Optional<Category> findById(Long id);

   Optional<Category> findByCategoryName(String categoryName);
}
