package com.mulei.blisscart.repository;

import com.mulei.blisscart.model.Category;
import com.mulei.blisscart.model.Product_Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<Product_Image, Long> {


    //Optional<Product_Image> findByUrl(String image_url);


}
