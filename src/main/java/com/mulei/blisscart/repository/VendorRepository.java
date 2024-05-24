package com.mulei.blisscart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mulei.blisscart.model.Vendor;


public interface VendorRepository extends JpaRepository<Vendor, Long>{


    Optional<Vendor> findByBusinessName(String businessName);
    
    Optional<Vendor> findByUserId(Long userId);

    Optional<Vendor> findById(Integer id);
}
