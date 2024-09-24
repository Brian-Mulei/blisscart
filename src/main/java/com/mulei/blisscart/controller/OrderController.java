package com.mulei.blisscart.controller;

import com.mulei.blisscart.dto.OrderDTO;
import com.mulei.blisscart.model.Order;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity<Order> addOrder(@RequestBody OrderDTO request) {

        return  ResponseEntity.ok(orderService.createOrder(request)) ;
    }



    @GetMapping("/customer/{customerId}")
    public ResourceResponse getCustomerOrders(@PathVariable Long customerId) {

        return  orderService.getCustomerOrders(customerId) ;
    }


    @GetMapping("/vendor/{vendorId}")
    public ResourceResponse getVendorSoldItems(@PathVariable Long vendorId) {

        return  orderService.getVendorSoldItems(vendorId) ;
    }

}
