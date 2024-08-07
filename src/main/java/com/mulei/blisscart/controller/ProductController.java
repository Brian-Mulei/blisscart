package com.mulei.blisscart.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mulei.blisscart.dto.ProductCreationDTO;
import com.mulei.blisscart.dto.ProductImageDTO;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.service.AWSService;
import com.mulei.blisscart.service.ProductService;


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
    public ResponseEntity<ResourceResponse> addProduct(@ModelAttribute ProductCreationDTO request) {

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
    
//   @PutMapping("/updatePrice")
//     public ResponseEntity<ResourceResponse> updatePrice(@RequestBody ProductDTO productDTO) {
 
//         return ResponseEntity.ok(productService.updatePrice(productDTO.getId(), productDTO.getPrice()))
//         ;
      
//     }

    @PostMapping("/deletePhoto")
    public ResponseEntity<ResourceResponse> deletePhoto(@RequestBody ProductImageDTO productImageDTO) throws Exception {

        return ResponseEntity.ok(productService.deleteFile(productImageDTO.getImage_url()));
    }
//     @PutMapping("/updateQuantity")
//     public ResponseEntity<ResourceResponse> updateQuantity(@RequestBody ProductDTO productUpdateDTO) {
              
//             return ResponseEntity.ok( productService.updateQuantity(productUpdateDTO.getId(), productUpdateDTO.getQuantity()));
  
// }

//@DeleteMapping(/)
}
