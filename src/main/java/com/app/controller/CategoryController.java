package com.app.controller;

import com.app.dto.CategoryDto;
import com.app.entity.Category;
import com.app.exception.ResourceNotFoundException;
import com.app.response.ApiResponse;
import com.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryDto categoryDto){
        ApiResponse categoryResponse = categoryService.createCategory(categoryDto);
        if (categoryResponse.getStatus()==false)
        {
            return new ResponseEntity<ApiResponse>(categoryResponse, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<ApiResponse>(categoryResponse, HttpStatus.CREATED);
        }
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable int categoryId) throws ResourceNotFoundException {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);

    }

    @GetMapping("/getById/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int categoryId) throws ResourceNotFoundException {
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        return category.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());

    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> allCategories = categoryService.getAllCategories();
        return new ResponseEntity<List<CategoryDto>>(allCategories,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId) throws ResourceNotFoundException {
        ApiResponse deleteResponse = categoryService.deleteCategory(categoryId);
        if (deleteResponse.getStatus()==false)
        {
            return new ResponseEntity<ApiResponse>(deleteResponse, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<ApiResponse>(deleteResponse, HttpStatus.CREATED);
        }
    }


}
