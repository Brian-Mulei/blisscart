package com.mulei.blisscart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulei.blisscart.model.Vendor;
import com.mulei.blisscart.repository.UserRepository;
import com.mulei.blisscart.repository.VendorRepository;


@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

   
  @Autowired
    private UserRepository userRepository;

    public  Optional<Vendor> findVendorByBusinessName(String businessName) {
        Optional<Vendor> vendorOpt = vendorRepository.findByBusinessName(businessName);
        return vendorOpt ;
    }

    public  Optional<Vendor> findVendorByUserId(Long userId) {
        Optional<Vendor> vendorOpt = vendorRepository.findByUserId(userId);
        return vendorOpt ;
    }

 
}
