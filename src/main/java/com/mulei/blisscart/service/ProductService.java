package com.mulei.blisscart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mulei.blisscart.dto.ProductCreationDTO;
import com.mulei.blisscart.dto.ProductDTO;
import com.mulei.blisscart.dto.ProductImageDTO;
import com.mulei.blisscart.dto.ProductVariationStockDTO;
import com.mulei.blisscart.dto.VariationDTO;
import com.mulei.blisscart.model.Product;
import com.mulei.blisscart.model.ProductStock;
import com.mulei.blisscart.model.ProductVariation;
import com.mulei.blisscart.model.Product_Image;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.repository.CategoryRepository;
import com.mulei.blisscart.repository.ProductImageRepository;
import com.mulei.blisscart.repository.ProductRepository;
import com.mulei.blisscart.repository.ProductStockRepository;
import com.mulei.blisscart.repository.ProductVariationRepository;
import com.mulei.blisscart.repository.VendorRepository;

import jakarta.transaction.Transactional;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class ProductService {
  
    private final ProductRepository productRepository;

  
    private final VendorRepository vendorRepository;

    private final CategoryRepository categoryRepository;

    private final ProductStockRepository productStockRepository;

    private final ProductVariationRepository productVariationRepository;


    private final ProductImageRepository productImageRepository;

    @Value("${amazon.s3.bucket-name}")
    String bucketName;

    @Autowired
    private final AWSService awsService;


    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, VendorRepository vendorRepository, AWSService awsService, ProductImageRepository productImageRepository, com.mulei.blisscart.repository.ProductStockRepository productStockRepository, com.mulei.blisscart.repository.ProductVariationRepository productVariationRepository) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
        this.categoryRepository = categoryRepository;
         this.productImageRepository = productImageRepository;
        this.awsService = awsService;
        this.productStockRepository = productStockRepository;
        this.productVariationRepository = productVariationRepository;
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
      //  product.setPrice(request.getPrice());
       // product.setQuantity(request.getQuantity());
        product.setDescription(request.getDescription());
       product.setImages(images);

        productRepository.save(product);   
        
           for (VariationDTO variationDTO : request.getVariations()) {
            ProductVariation variation = new ProductVariation();
            variation.setAttribute(variationDTO.getAttribute());
            variation.setValue(variationDTO.getValue());
            variation.setProduct(product);
            variation = productVariationRepository.save(variation);

            // Save stock for each variation
            for (ProductVariationStockDTO stockDTO : request.getStock()) {
                if (stockDTO.getAttribute().equals(variationDTO.getAttribute())
                        && stockDTO.getValue().equals(variationDTO.getValue())) {
                    ProductStock stock = new ProductStock();
                    stock.setAttribute(stockDTO.getAttribute());
                    stock.setValue(stockDTO.getValue());
                    stock.setQuantity(stockDTO.getQuantity());
                    stock.setProduct(product);
                    productStockRepository.save(stock);
                }
            }
        }


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
           Boolean successful_deletion=   awsService.deleteFile(url);

            if(successful_deletion){

                    return new ResourceResponse(null, "Deleted Successfully", true);


            }else{
                return new ResourceResponse(null, "Unable to delete", false);

            }



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
        productDTO.setVariations(
            product.getVariations()
            .stream().map(this::convertVariationToDTO).collect(Collectors.toList()));
        productDTO.setStock(product.getStock().stream().map(this::convertStockToDTO).collect(Collectors.toList()));
     
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

    private VariationDTO convertVariationToDTO(ProductVariation variation) {
        VariationDTO variationDTO = new VariationDTO();
        variationDTO.setAttribute(variation.getAttribute());
        variationDTO.setValue(variation.getValue());
        return variationDTO;
    }

    private ProductVariationStockDTO convertStockToDTO(ProductStock stock) {
        ProductVariationStockDTO stockDTO = new ProductVariationStockDTO();
        stockDTO.setAttribute(stock.getAttribute());
        stockDTO.setValue(stock.getValue());
        stockDTO.setQuantity(stock.getQuantity());
        return stockDTO;
    }
    
}
