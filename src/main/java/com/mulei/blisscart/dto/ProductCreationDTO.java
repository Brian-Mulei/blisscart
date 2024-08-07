package com.mulei.blisscart.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductCreationDTO {


    private Long id;
    private Long vendorId;
    private Long categoryId;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    List<MultipartFile> images;

    private List<VariationDTO> variations;
    private List<ProductVariationStockDTO> stock;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public List<VariationDTO> getVariations() {
        return variations;
    }

    public void setVariations(List<VariationDTO> variations) {
        this.variations = variations;
    }


    public List<ProductVariationStockDTO> getStock() {
        return stock;
    }

    public void setStock(List<ProductVariationStockDTO> stock) {
        this.stock = stock;
    }
}
