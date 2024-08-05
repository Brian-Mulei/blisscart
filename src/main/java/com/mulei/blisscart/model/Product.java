package com.mulei.blisscart.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable=true)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product_Image> images;

    // public Product(String description, Long id, String name, Category category, Double price, Integer quantity, Vendor vendor) {
    //     this.description = description;
    //     this.id = id;
    //     this.name = name;
    //     this.price = price;
    //     this.quantity = quantity;
    //     this.vendor = vendor;
    //     this.category=category;
    // }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product_Image> getImages() {
        return images;
    }

    public void setImages(List<Product_Image> images) {
        this.images = images;
    }
}