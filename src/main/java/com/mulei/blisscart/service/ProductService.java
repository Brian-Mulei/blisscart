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
import com.mulei.blisscart.dto.ProductVariationDTO;
import com.mulei.blisscart.model.Product;
import com.mulei.blisscart.model.ProductImage;
import com.mulei.blisscart.model.ProductVariation;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.repository.CategoryRepository;
import com.mulei.blisscart.repository.ProductImageRepository;
import com.mulei.blisscart.repository.ProductRepository;
import com.mulei.blisscart.repository.ProductVariationRepository;
import com.mulei.blisscart.repository.VendorRepository;

import jakarta.transaction.Transactional;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final VendorRepository vendorRepository;

    private final CategoryRepository categoryRepository;

    private final ProductImageRepository productImageRepository;

    private final ProductVariationRepository productVariationRepository;

    @Value("${amazon.s3.bucket-name}")
    String bucketName;

    @Autowired
    private final AWSService awsService;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, VendorRepository vendorRepository, AWSService awsService, ProductImageRepository productImageRepository, com.mulei.blisscart.repository.ProductVariationRepository productVariationRepository) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
        this.categoryRepository = categoryRepository;
        this.productImageRepository = productImageRepository;
        this.awsService = awsService;
        this.productVariationRepository = productVariationRepository;
    }

    public ResourceResponse addProduct(ProductCreationDTO request, List<String> image_urls) {

        if (vendorRepository.findById(request.getVendorId()).isEmpty()) {
            return new ResourceResponse(null, "Vendor not found", false);
        }

        if (categoryRepository.findById(request.getCategoryId()).isEmpty()) {
            return new ResourceResponse(null, "Invalid Category", false);
        }

        Product product = new Product();

        List<ProductImage> images = image_urls.stream()
                .map(url -> {
                    ProductImage image = new ProductImage();
                    image.setImage_url(url);
                    image.setProduct(product);
                    return image;
                })
                .collect(Collectors.toList());



        product.setCategory(categoryRepository.findById(request.getCategoryId()).get());
        product.setVendor(vendorRepository.findById(request.getVendorId()).get());
        product.setName(request.getName());

        product.setDescription(request.getDescription());
        product.setImages(images);

        productRepository.save(product);

        if(!request.getVariations().isEmpty()){
            List<ProductVariation> variations = request.getVariations().stream()
            .map(variant -> {
                ProductVariation variation = new ProductVariation();

                variation.setProduct(product);
                variation.setPrice(variant.getPrice());
                variation.setQuantity(variant.getQuantity());
                variation.setVariationDescription(variant.getVariationDescription());

                return variation;
            })
            .collect(Collectors.toList());
            product.setVariations(variations);

        }


        productRepository.save(product);
        return new ResourceResponse(null, "Added Successfully", true);

    }


    public ResourceResponse getProducts(int page, int size) {

        Page<Product> products = productRepository.findAll(PageRequest.of(page, size));

        // return new ResourceResponse(products, "Added Successfully", true);
        List<ProductDTO> productDTOs = products.stream().map(this::convertToDTO).collect(Collectors.toList());

        return new ResourceResponse(productDTOs, "Fetched successfully", true);
    }

    @Transactional
    public ResourceResponse updatePrice(Long productVariationId, Double newPrice) {
        try {

            if (productVariationRepository.findById(productVariationId).isEmpty()) {
                return new ResourceResponse(null, "Product not found", false);
            }

            productVariationRepository.updatePrice(productVariationId, newPrice);
            return new ResourceResponse(null, "Updated Successfully", true);

        } catch (Exception e) {

            return new ResourceResponse(null, "Failed to Update", false);

        }
    }

    @Transactional
    public ResourceResponse updateQuantity(Long productVariationId, Integer newQuantity) {

        try {
            if (productVariationRepository.findById(productVariationId).isEmpty()) {
                return new ResourceResponse(null, "Product not found", false);
            }

            productVariationRepository.updateQuantity(productVariationId, newQuantity);
            return new ResourceResponse(null, "Updated Successfully", true);

        } catch (Exception e) {

            return new ResourceResponse(null, "Failed to Update", false);

        }

    }

    public ResourceResponse deleteFile(String url) throws Exception {

        try {
            Boolean successful_deletion = awsService.deleteFile(url);

            if (successful_deletion) {

                return new ResourceResponse(null, "Deleted Successfully", true);

            } else {
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

        List<ProductVariationDTO> variations = product.getVariations().stream()
                .map(this::convertToProductVariation)
                .toList();

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setVendorId(product.getVendor().getId());
        productDTO.setVendorName(product.getVendor().getBusinessName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());

        productDTO.setImages(imageUrls);
        productDTO.setVariations(variations);
        return productDTO;
    }

    private ProductImageDTO convertToImageDTO(ProductImage image) {
        return new ProductImageDTO(
                image.getId(),
                image.getImage_url());
    }

    private ProductVariationDTO convertToProductVariation(ProductVariation variation) {

        return new ProductVariationDTO(
                variation.getId(),
                variation.getVariationDescription(),
                variation.getPrice(),
                variation.getQuantity()

        );

    }

}
