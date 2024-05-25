package com.mulei.blisscart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mulei.blisscart.dto.CategoryDTO;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.service.CategoryService;


@RestController
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<ResourceResponse> addCategorResponseEntity(@RequestBody CategoryDTO request) {
         
        return  ResponseEntity.ok(categoryService.addCategory(request));
    }
    



}
