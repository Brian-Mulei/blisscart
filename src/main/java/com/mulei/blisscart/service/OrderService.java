package com.mulei.blisscart.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.mulei.blisscart.dto.OrderDTO;
import com.mulei.blisscart.dto.OrderItemDTO;
import com.mulei.blisscart.enums.OrderStatus;
import com.mulei.blisscart.model.Order;
import com.mulei.blisscart.model.OrderItem;
import com.mulei.blisscart.model.ProductVariation;
import com.mulei.blisscart.model.User;
import com.mulei.blisscart.repository.OrderRepository;
import com.mulei.blisscart.repository.ProductRepository;
import com.mulei.blisscart.repository.ProductVariationRepository;
import com.mulei.blisscart.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductVariationRepository productVariationRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
            UserRepository userRepository,
            com.mulei.blisscart.repository.ProductVariationRepository productVariationRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productVariationRepository = productVariationRepository;
    }

    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        // Create new order
        Order order = new Order();
        order.setCustomer(findCustomerById(orderDTO.getCustomerId())); // Assuming a method to find the customer
        order.setStatus(OrderStatus.PENDING_PAYMENT);

        // Add order items
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            ProductVariation variant = productVariationRepository.findById(itemDTO.getproductVariationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Variant not found"));

            // Check if there is enough stock
            if (variant.getQuantity() < itemDTO.getQuantity()) {
                throw new IllegalArgumentException(
                        "Insufficient stock for product: " + variant.getVariationDescription());
            }

            // Update product quantity
            variant.setQuantity(variant.getQuantity() - itemDTO.getQuantity());
            productVariationRepository.save(variant);

            // Create and add order item
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setVariation(variant);
            orderItem.setPerPrice(itemDTO.getPerPrice());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setSubTotal(itemDTO.getSubTotal());

            order.getItems().add(orderItem);
        }

        // Save order
        return orderRepository.save(order);
    }

    private User findCustomerById(Long customerId) {

        User customer = userRepository.findById(customerId).get();

        return customer;
        // Implement customer retrieval logic
    }
}
