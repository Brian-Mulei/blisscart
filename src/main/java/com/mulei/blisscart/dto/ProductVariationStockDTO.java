package com.mulei.blisscart.dto;

public class ProductVariationStockDTO {
    private String attribute;
    private String value;
    private int quantity;


       public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }  
    
    public int getQuantity() {
        return quantity;
    }



    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }   
    
   
}


