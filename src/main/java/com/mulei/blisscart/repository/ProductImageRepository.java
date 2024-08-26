package com.mulei.blisscart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

 import com.mulei.blisscart.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {


  //  Optional<Product_Image> findByimage_url(String image_url);


}
