package com.mulei.blisscart.controller;

import com.mulei.blisscart.dto.ProductImageDTO;
import com.mulei.blisscart.service.AWSService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mulei.blisscart.dto.ProductCreationDTO;
import com.mulei.blisscart.dto.ProductDTO;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.service.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/product")
public class ProductController {

     private final ProductService productService;

     private final AWSService awsService;

    public ProductController(ProductService productService, AWSService awsService) {
        this.productService = productService;
        this.awsService = awsService;
    }


    @PostMapping("")
    public ResponseEntity<ResourceResponse> addProduct(@RequestBody ProductCreationDTO request) {

        try{
        List<String> imageUrls = request.getImages().stream()
                .map(file -> {
                    try {
                        return awsService.uploadFile(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(productService.addProduct(request,imageUrls));


    } catch (Exception e) {
        return ResponseEntity.status(500).body(null); // Handle errors appropriately
    }
    }
    


    @GetMapping("")
    public  ResponseEntity<ResourceResponse>  getAllProducts( 
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(productService.getProducts( page,size));
    }
    


    @PostMapping("/deletePhoto")
    public ResponseEntity<ResourceResponse> deletePhoto(@RequestBody ProductImageDTO productImageDTO) throws Exception {

        return ResponseEntity.ok(productService.deleteFile(productImageDTO.getImage_url()));
    }


//@DeleteMapping(/)
}
