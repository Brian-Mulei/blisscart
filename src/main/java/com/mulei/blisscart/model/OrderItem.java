package com.mulei.blisscart.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items"
)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id",nullable = false)
    private Order order;


    @ManyToOne
    @JoinColumn(name = "product_variation_id", nullable = false )
    private ProductVariation variation;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name="per_price")
    private Double perPrice;

    @Column(name="sub_total")
    private Double subTotal;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public ProductVariation getVariation() {
        return variation;
    }

    public void setVariation(ProductVariation variation) {
        this.variation = variation;
    }

    
}
