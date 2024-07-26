package com.mulei.blisscart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mulei.blisscart.dto.ProductCreationDTO;
import com.mulei.blisscart.dto.ProductDTO;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.service.ProductService;




@RestController
@RequestMapping("/api/product")
public class ProductController {

     private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("")
    public ResponseEntity<ResourceResponse> addProduct(@RequestBody ProductCreationDTO request) {
               
        return ResponseEntity.ok(productService.addProduct(request));
    }
    


    @GetMapping("")
    public  ResponseEntity<ResourceResponse>  getAllProducts( 
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(productService.getProducts( page,size));
    }
    
  @PutMapping("/updatePrice")
    public ResponseEntity<ResourceResponse> updatePrice(@RequestBody ProductDTO productDTO) {
 
        return ResponseEntity.ok(productService.updatePrice(productDTO.getId(), productDTO.getPrice()))
        ;
      
    }


    @PutMapping("/updateQuantity")
    public ResponseEntity<ResourceResponse> updateQuantity(@RequestBody ProductDTO productUpdateDTO) {
              
            return ResponseEntity.ok( productService.updateQuantity(productUpdateDTO.getId(), productUpdateDTO.getQuantity()));
  
}
}
