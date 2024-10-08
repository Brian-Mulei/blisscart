package com.mulei.blisscart.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_images")
public class ProductImage {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @Column(name = "image_url", nullable = false)
    private String image_url;



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }


}
