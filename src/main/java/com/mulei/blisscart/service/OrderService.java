package com.mulei.blisscart.service;

import com.mulei.blisscart.model.User;
import com.mulei.blisscart.repository.OrderRepository;
import com.mulei.blisscart.repository.ProductRepository;
import com.mulei.blisscart.repository.ProductStockRepository;
import com.mulei.blisscart.repository.UserRepository;

public class OrderService {

     private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final ProductStockRepository productStockRepository;

     public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository, com.mulei.blisscart.repository.ProductStockRepository productStockRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
         this.productStockRepository = productStockRepository;
    }

    //     @Transactional
    // public Order createOrder(OrderDTO orderDTO) {
    //     // Create new order
    //     Order order = new Order();
    //     order.setCustomer(findCustomerById(orderDTO.getCustomerId())); // Assuming a method to find the customer
    //     order.setStatus(OrderStatus.PENDING_PAYMENT);

    //     // Add order items
    //     for (OrderItemDTO itemDTO : orderDTO.getItems()) {
    //         Product product = productRepository.findById(itemDTO.getProductId())
    //                                            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    //         // Check if there is enough stock
    //         if (product.getQuantity() < itemDTO.getQuantity()) {
    //             throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
    //         }

    //         // Update product quantity
    //         product.setQuantity(product.getQuantity() - itemDTO.getQuantity());
    //         productRepository.save(product);

    //         // Create and add order item
    //         OrderItem orderItem = new OrderItem();
    //         orderItem.setOrder(order);
    //         orderItem.setProduct(product);
    //         orderItem.setQuantity(itemDTO.getQuantity());

    //         order.getItems().add(orderItem);
    //     }

    //     // Save order
    //     return orderRepository.save(order);
    // }



//   @Transactional
//     public OrderDTO createOrder(OrderDTO orderDTO) {
//         Order order = new Order();
//         order.setCustomer(userRepository.findById(orderDTO.getCustomerId()).get()); 
//         order = orderRepository.save(order);

//         for (OrderItemDTO itemDTO : orderDTO.getItems()) {
//             // Find the variation
//             ProductVariation variation =   findVariationById(itemDTO.getVariationId());

//             // Update stock quantity
//             ProductStock stock = productStockRepository.findByProductAndAttributeAndValue(
//                     variation.getProduct(), variation.getAttribute(), variation.getValue());
//             if (stock.getQuantity() < itemDTO.getQuantity()) {
//                 throw new Exception("Not enough stock for " + variation.getAttribute() + " " + variation.getValue());
//             }
//             stock.setQuantity(stock.getQuantity() - itemDTO.getQuantity());
//             productStockRepository.save(stock);

//             // Save order item
//             OrderItem item = new OrderItem();
//             item.setOrder(order);
//             item.setVariation(variation);
//             item.setQuantity(itemDTO.getQuantity());
//             order.getItems().add(item);
//         }

//         return convertToDTO(order);
//     }

    private User findCustomerById(Long customerId) {

        User customer =   userRepository.findById(customerId).get();

        return customer;
        // Implement customer retrieval logic
    }


    // private OrderDTO convertToDTO(Order order) {
    //     OrderDTO orderDTO = new OrderDTO();
    //     orderDTO.setId(order.getId());
    //     orderDTO.setCustomerId(order.getCustomer().getId() ); 
    //     orderDTO.setItems(order.getItems().stream()
    //             .map(this::convertOrderItemToDTO)
    //             .collect(Collectors.toList()));
    //     return orderDTO;
    // }
 
    // private OrderItemDTO convertOrderItemToDTO(OrderItem orderItem) {

    //     OrderItemDTO orderItemDTO = new OrderItemDTO();
    //     orderItemDTO.setproductVariationId(orderItem.getVariation().getId());
    //     orderItemDTO.setAttribute(orderItem.getVariation().getAttribute());
    //     orderItemDTO.setValue(orderItem.getVariation().getValue());
    //     orderItemDTO.setQuantity(orderItem.getQuantity());
    //     return orderItemDTO;
    // }


}
