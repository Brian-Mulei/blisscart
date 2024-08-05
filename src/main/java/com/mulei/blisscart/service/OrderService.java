package com.mulei.blisscart.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.mulei.blisscart.dto.OrderDTO;
import com.mulei.blisscart.dto.OrderItemDTO;
import com.mulei.blisscart.enums.OrderStatus;
import com.mulei.blisscart.model.Order;
import com.mulei.blisscart.model.OrderItem;
import com.mulei.blisscart.model.Product;
import com.mulei.blisscart.model.User;
import com.mulei.blisscart.repository.OrderRepository;
import com.mulei.blisscart.repository.ProductRepository;
import com.mulei.blisscart.repository.UserRepository;

import jakarta.transaction.Transactional;

public class OrderService {

     private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

     public OrderService(OrderRepository orderRepository, ProductRepository productRepository,UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

        @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        // Create new order
        Order order = new Order();
        order.setCustomer(findCustomerById(orderDTO.getCustomerId())); // Assuming a method to find the customer
        order.setStatus(OrderStatus.PENDING_PAYMENT);

        // Add order items
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                                               .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            // Check if there is enough stock
            if (product.getQuantity() < itemDTO.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }

            // Update product quantity
            product.setQuantity(product.getQuantity() - itemDTO.getQuantity());
            productRepository.save(product);

            // Create and add order item
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());

            order.getItems().add(orderItem);
        }

        // Save order
        return orderRepository.save(order);
    }

    private User findCustomerById(Long customerId) {

        User customer =   userRepository.findById(customerId).get();

        return customer;
        // Implement customer retrieval logic
    }
}
