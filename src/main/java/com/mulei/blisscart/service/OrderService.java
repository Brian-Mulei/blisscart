package com.mulei.blisscart.service;

import com.mulei.blisscart.dto.OrderCreationDTO;
import com.mulei.blisscart.dto.OrderResponseDTO;
import com.mulei.blisscart.reponse.OrderResponse;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.repository.*;
import com.mulei.blisscart.utils.ReferenceNumberGenerator;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.mulei.blisscart.dto.OrderDTO;
import com.mulei.blisscart.dto.OrderItemDTO;
import com.mulei.blisscart.enums.OrderStatus;
import com.mulei.blisscart.model.Order;
import com.mulei.blisscart.model.OrderItem;
import com.mulei.blisscart.model.ProductVariation;
import com.mulei.blisscart.model.User;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ProductVariationRepository productVariationRepository;
    private final UserRepository userRepository;

    private ReferenceNumberGenerator referenceNumberGenerator;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository,
                        UserRepository userRepository,
                        ProductVariationRepository productVariationRepository,ReferenceNumberGenerator referenceNumberGenerator) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productVariationRepository = productVariationRepository;
        this.referenceNumberGenerator=  referenceNumberGenerator;
    }

    @Transactional
    public OrderResponse createOrder(OrderCreationDTO orderDTO) {
        // Create new order
        Order order = new Order();
        order.setCustomer(findCustomerById(orderDTO.getCustomerId()));
        order.setStatus(OrderStatus.PENDING_PAYMENT);

        String referenceNumber = referenceNumberGenerator.generateUniqueReferenceNumber(6, 9);

        order.setorderTime(LocalDateTime.now());


        order.setTotal(orderDTO.getTotal());

        order.setReferenceNumber(referenceNumber);

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            ProductVariation variant = productVariationRepository.findById(itemDTO.getproductVariationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Variant not found"));

            // Check if there is enough stock
            if (variant.getQuantity() < itemDTO.getQuantity()) {
                return new OrderResponse( null, "Insufficient Quantity", false);

//                throw new IllegalArgumentException(
//                        "Insufficient stock for product: " + variant.getVariationDescription());
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


            orderItems.add(orderItem);

        }
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO(
                savedOrder.getReferenceNumber(),
                savedOrder.getorderTime(),
                savedOrder.getStatus(),
                savedOrder.getTotal()
        );

        return new OrderResponse( orderResponseDTO, "Order Saved", true);

    }

    public ResourceResponse getCustomerOrders(Long customerId){


        if(userRepository.findById(customerId).isPresent()) {
            List<Order> customerOrders = orderRepository.findByCustomer(userRepository.findById(customerId).get());

            return new ResourceResponse(customerOrders, "Retrieved", true);
        }else{
            return new ResourceResponse(null, "User not found", false);

        }

    }

    public ResourceResponse getVendorSoldItems(Long vendorId){


        if(userRepository.findById(vendorId).isPresent()) {

            List<OrderItem> vendorSoldItems = orderItemRepository.findSoldProductsByVendor(vendorId);

            return new ResourceResponse(vendorSoldItems, "Retrieved", true);
        }else{
            return new ResourceResponse(null, "User not found", false);

        }

    }


    private User findCustomerById(Long customerId) {

        return userRepository.findById(customerId).get();
        // Implement customer retrieval logic
    }
}
