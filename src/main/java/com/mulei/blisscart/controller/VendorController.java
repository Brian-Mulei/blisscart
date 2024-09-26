package com.mulei.blisscart.controller;

import com.mulei.blisscart.dto.ProductDTO;
import com.mulei.blisscart.dto.ProductVariationDTO;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    private final ProductService productService;

    public VendorController(ProductService productService) {
        this.productService = productService;
    }


    @PutMapping("/variation/{variationId}/update-price")
    public  ResourceResponse updateVariationPrice(
            @PathVariable Long variationId,
            @RequestParam Double newPrice) {

         return productService.updatePrice(variationId, newPrice);
    }



    @PutMapping("/variation/{variationId}/update-quantity")
    public  ResourceResponse updateVariationQuantity(
            @PathVariable Long variationId,
            @RequestParam int newQuantity) {

        return  productService.updateQuantity(variationId, newQuantity);
    }


    @PutMapping("/product/{productId}/add-variant")
    public  ResourceResponse addVariation(
            @PathVariable Long productId,
            @RequestBody List<ProductVariationDTO> request) {

        return  productService.addProductVariation(request, productId);
    }


}
