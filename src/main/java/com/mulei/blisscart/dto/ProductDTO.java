package com.mulei.blisscart.dto;

public class ProductDTO {

    private Long id;
    private Long vendorId;
    private Long categoryId;
    private String name;
    private String description;
    private Double price;
    private Integer quantity; 

    public ProductDTO(Long categoryId, String description, Long id, String name, Double price, Integer quantity, Long vendorId) {
        this.categoryId = categoryId;
        this.description = description;
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.vendorId = vendorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
