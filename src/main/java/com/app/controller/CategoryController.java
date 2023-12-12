package com.app.controller;

import com.app.dto.CategoryDto;
import com.app.response.ApiResponse;
import com.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryDto categoryDto){
        ApiResponse categoryResponse = categoryService.createCategory(categoryDto);
        return null;
    }
}
