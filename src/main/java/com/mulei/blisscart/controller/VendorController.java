package com.mulei.blisscart.controller;

import com.mulei.blisscart.dto.ProductDTO;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    private final ProductService productService;

    public VendorController(ProductService productService) {
        this.productService = productService;
    }


    @PutMapping("/variation/{variationId}/update-quantity")
    public ResponseEntity<ResourceResponse> updateVariationQuantity(
            @PathVariable Long variationId,
            @RequestParam Double newQuantity) {

         return ResponseEntity.ok(productService.updatePrice(variationId, newQuantity));
    }



    @PutMapping("/variation/{variationId}/update-quantity")
    public ResponseEntity<ResourceResponse> updateVariationPrice(
            @PathVariable Long variationId,
            @RequestParam int newQuantity) {

        return ResponseEntity.ok(productService.updateQuantity(variationId, newQuantity));
    }


}
