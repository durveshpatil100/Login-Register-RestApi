package com.app.service;

import com.app.dto.CategoryDto;
import com.app.entity.Category;
import com.app.exception.ResourceNotFoundException;
import com.app.response.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    ApiResponse createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) throws ResourceNotFoundException;
    ApiResponse deleteCategory(int categoryId) throws ResourceNotFoundException;
    Optional<Category> getCategoryById(int categoryId);
    List<CategoryDto> getAllCategories();
}
