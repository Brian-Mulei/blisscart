package com.mulei.blisscart.dto;

public class OrderItemDTO {

    private Long id;
 
    private Long productVariationId;
 

 
    private Integer quantity;

    private Double perPrice;

    private Double subTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 

    public Long getproductVariationId() {
        return productVariationId;
    }

    public void setproductVariationId(Long productVariationId) {
        this.productVariationId = productVariationId;
    }


 

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPerPrice() {
        return perPrice;
    }

    public void setPerPrice(Double perPrice) {
        this.perPrice = perPrice;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }



    
}
