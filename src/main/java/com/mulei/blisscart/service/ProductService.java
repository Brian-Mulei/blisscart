package com.mulei.blisscart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mulei.blisscart.dto.ProductDTO;
import com.mulei.blisscart.model.Product;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.repository.CategoryRepository;
import com.mulei.blisscart.repository.ProductRepository;
import com.mulei.blisscart.repository.VendorRepository;

@Service
public class ProductService {
  
    private final ProductRepository productRepository;

  
    private final VendorRepository vendorRepository;

    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
        this.categoryRepository = categoryRepository;
    }


    public ResourceResponse addProduct(ProductDTO request){

        if(vendorRepository.findById(request.getVendorId()).isEmpty()){
            return new ResourceResponse(null, "Vendor not found", false);
        }

        if(categoryRepository.findById(request.getCategoryId()).isEmpty()){
            return new ResourceResponse(null, "Invalid Category", false);
        }


        Product product = new Product();

        product.setCategory(categoryRepository.findById(request.getCategoryId()).get());
        product.setVendor(vendorRepository.findById(request.getVendorId()).get());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setDescription(request.getDescription());

        productRepository.save(product);      

        return new ResourceResponse(null, "Added Successfully", true);

    }
    
    public ResourceResponse getProducts(){

     
        List<Product>products = productRepository.findAll();

    //    return new ResourceResponse(products, "Added Successfully", true);
        List<ProductDTO> productDTOs = products.stream().map(this::convertToDTO).collect(Collectors.toList());
        return new ResourceResponse(productDTOs, "Fetched successfully", true);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO(
        );
        productDTO.setId(product.getId());
       productDTO.setVendorId(product.getVendor().getId());
       productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        return productDTO;
    }

  

}
