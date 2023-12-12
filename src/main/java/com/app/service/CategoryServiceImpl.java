package com.app.service;

import com.app.dto.CategoryDto;
import com.app.entity.Category;
import com.app.repo.CategoryRepo;
import com.app.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    
    @Autowired
    private CategoryRepo categoryRepo;
    
    @Override
    public ApiResponse createCategory(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getCategoryName());
        Category save = categoryRepo.save(category);
        return null;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public ApiResponse deleteCategory(int categoryId) {
      return null;
    }

    @Override
    public CategoryDto getCategory(int categoryId) {
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return null;
    }
}
