package com.mulei.blisscart.dto;

public class ProductImageDTO {

    private Long id;


    private String image_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    public ProductImageDTO(Long id, String image_url) {
        this.id = id;
        this.image_url = image_url;
    }
}
