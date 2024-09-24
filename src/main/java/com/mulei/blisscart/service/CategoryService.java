package com.mulei.blisscart.service;

import com.mulei.blisscart.dto.ProductDTO;
import com.mulei.blisscart.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mulei.blisscart.dto.CategoryDTO;
import com.mulei.blisscart.model.Category;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryService {


        private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    public ResourceResponse addCategory(CategoryDTO request){

        if(categoryRepository.findByCategoryName(request.getName()).isPresent()){
            return new ResourceResponse(null, "Category with name exists", false);
        }

        Category category=new Category();


        category.setCategoryName(request.getName());

        categoryRepository.save(category);

        return new ResourceResponse(null, "Added Successfully", true);


    }

    public ResourceResponse getCategories( ) {

        List<Category> categories = categoryRepository.findAll();

        // return new ResourceResponse(products, "Added Successfully", true);

        return new ResourceResponse(categories, "Fetched successfully", true);
    }
}
