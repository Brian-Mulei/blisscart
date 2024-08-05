package com.mulei.blisscart.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id",nullable = false)
    private Order order;


    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

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
}
