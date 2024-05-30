package com.mulei.blisscart.service;

import org.springframework.stereotype.Service;

import com.mulei.blisscart.dto.CategoryDTO;
import com.mulei.blisscart.model.Category;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.repository.CategoryRepository;


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
}
