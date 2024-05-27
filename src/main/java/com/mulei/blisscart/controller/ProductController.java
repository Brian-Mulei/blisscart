package com.mulei.blisscart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mulei.blisscart.dto.ProductDTO;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.service.ProductService;




@RestController
public class ProductController {

     private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/product")
    public ResponseEntity<ResourceResponse> addProduct(@RequestBody ProductDTO request) {
               
        return ResponseEntity.ok(productService.addProduct(request));
    }
    


    @GetMapping("/product")
    public  ResponseEntity<ResourceResponse>  getAllPoducts(   ) {
        return ResponseEntity.ok(productService.getProducts());
    }
    

}
