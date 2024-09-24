package com.mulei.blisscart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mulei.blisscart.dto.CategoryDTO;
import com.mulei.blisscart.reponse.ResourceResponse;
import com.mulei.blisscart.service.CategoryService;


@RestController
@RequestMapping("/api/category")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public ResponseEntity<ResourceResponse> addCategorResponseEntity(@RequestBody CategoryDTO request) {
         
        return  ResponseEntity.ok(categoryService.addCategory(request));
    }


    @GetMapping("")
    public  ResponseEntity<ResourceResponse>  getAllProducts() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

}
