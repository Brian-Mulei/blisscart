package com.mulei.blisscart.dto;

public class VendorDTO {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private Long userId;
    private String businessName;
    private String address;

//    public VendorDTO(String address, String businessName, Long userId) {
//        this.address = address;
//        this.businessName = businessName;
//
//        this.userId = userId;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

   
    
}
