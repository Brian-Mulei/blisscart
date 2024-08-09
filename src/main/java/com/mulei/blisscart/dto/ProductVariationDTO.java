package com.mulei.blisscart.dto;

public class ProductVariationDTO {

    private Long id;


    private String variationDescription; 
    
    private Double price;


    private Integer quantity;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

 
    


    public ProductVariationDTO(Long id, String variationDescription, Double price, Integer quantity) {
        this.id = id;
        this.variationDescription = variationDescription;
        this.price = price;
        this.quantity = quantity;
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


    public String getVariationDescription() {
        return variationDescription;
    }


    public void setVariationDescription(String variationDescription) {
        this.variationDescription = variationDescription;
    }



    
}
