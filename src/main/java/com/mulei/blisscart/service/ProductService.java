package com.mulei.blisscart.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mulei.blisscart.dto.ProductImageDTO;
import com.mulei.blisscart.model.Product_Image;
import com.mulei.blisscart.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mulei.blisscart.dto.ProductCreationDTO;
import com.mulei.blisscart.dto.ProductDTO;
import com.mulei.blisscart.model.Product;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.repository.CategoryRepository;
import com.mulei.blisscart.repository.ProductRepository;
import com.mulei.blisscart.repository.VendorRepository;

import jakarta.transaction.Transactional;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class ProductService {
  
    private final ProductRepository productRepository;

  
    private final VendorRepository vendorRepository;

    private final CategoryRepository categoryRepository;

    private final  AWSService awsService;


    private final ProductImageRepository productImageRepository;

    @Value("${amazon.s3.bucket-name}")
    String bucketName;

     private final S3Client s3Client;


    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, VendorRepository vendorRepository, AWSService awsService, ProductImageRepository productImageRepository, S3Client s3Client) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
        this.categoryRepository = categoryRepository;
        this.awsService = awsService;
        this.productImageRepository = productImageRepository;
        this.s3Client = s3Client;
    }


    public ResourceResponse addProduct(ProductCreationDTO request, List<String> image_urls){

        if(vendorRepository.findById(request.getVendorId()).isEmpty()){
            return new ResourceResponse(null, "Vendor not found", false);
        }

        if(categoryRepository.findById(request.getCategoryId()).isEmpty()){
            return new ResourceResponse(null, "Invalid Category", false);
        }


        Product product = new Product();


        List<Product_Image> images = image_urls.stream()
                .map(url -> {
                    Product_Image image = new Product_Image();
                    image.setImage_url(url);
                    image.setProduct(product);
                    return image;
                })
                .collect(Collectors.toList());


        product.setCategory(categoryRepository.findById(request.getCategoryId()).get());
        product.setVendor(vendorRepository.findById(request.getVendorId()).get());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setDescription(request.getDescription());
       product.setImages(images);

        productRepository.save(product);      

        return new ResourceResponse(null, "Added Successfully", true);

    }
    
    public ResourceResponse getProducts(int page, int size){

     
        Page<Product>products = productRepository.findAll(PageRequest.of(page, size));

    //    return new ResourceResponse(products, "Added Successfully", true);
        List<ProductDTO> productDTOs = products.stream().map(this::convertToDTO).collect(Collectors.toList());



        return new ResourceResponse(productDTOs, "Fetched successfully", true);
    }



    
    @Transactional
    public ResourceResponse updatePrice(Long productId, Double newPrice) {
        try {

            if(productRepository.findById(productId).isEmpty()){
                return new ResourceResponse(null, "Product not found", false);
            }

             productRepository.updatePrice(productId, newPrice);         
             return new ResourceResponse(null, "Updated Successfully", true);
 

        } catch (Exception e) {
        
            return new ResourceResponse( null, "Failed to Update", false);

        }
    }


    @Transactional
    public ResourceResponse updateQuantity(Long productId, Integer newQuantity){

        try {
            if(productRepository.findById(productId).isEmpty()){
                return new ResourceResponse(null, "Product not found", false);
            }

             productRepository.updateQuantity(productId, newQuantity);        
             return new ResourceResponse(null, "Updated Successfully", true);
 

        } catch (Exception e) {

            return new ResourceResponse(null, "Failed to Update", false);

        }  

    }


    public ResourceResponse deleteFile(String url) throws Exception{

        try{
            DeleteObjectRequest deleteObjectRequest=  DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(url).build();

            s3Client.deleteObject(deleteObjectRequest);


            //productImageRepository.delete(productImageRepository.findByUrl(url));

            return new ResourceResponse(null, "Deleted Successfully", true);


        } catch (S3Exception e) {
            return new ResourceResponse(null, "Unable to delete", false);

        }

    }
    private ProductDTO convertToDTO(Product product) {

        List<ProductImageDTO> imageUrls = product.getImages().stream()
                .map(this::convertToImageDTO)
                .toList();

        ProductDTO productDTO = new ProductDTO(
        );
        productDTO.setId(product.getId());
       productDTO.setVendorId(product.getVendor().getId());
       productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setImages(imageUrls);
        return productDTO;
    }

    private ProductImageDTO convertToImageDTO(Product_Image image) {
        return new  ProductImageDTO
            (
                image.getId(),
                image.getImage_url()
        );
    }

  

}
