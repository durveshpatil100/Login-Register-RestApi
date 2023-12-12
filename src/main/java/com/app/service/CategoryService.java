package com.app.service;

import com.app.dto.CategoryDto;
import com.app.response.ApiResponse;

import java.util.List;

public interface CategoryService {

    ApiResponse createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto);
    ApiResponse deleteCategory(int categoryId);
    CategoryDto getCategory(int categoryId);
    List<CategoryDto> getAllCategories();
}
