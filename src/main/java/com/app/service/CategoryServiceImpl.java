package com.app.service;

import com.app.dto.CategoryDto;
import com.app.entity.Category;
import com.app.exception.ResourceNotFoundException;
import com.app.repo.CategoryRepo;
import com.app.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public ApiResponse createCategory(CategoryDto categoryDto) {

        Category category =  this.modelMapper.map(categoryDto, Category.class);
        categoryRepo.save(category);
        return new ApiResponse("Category is saved", true);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) throws ResourceNotFoundException {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        category.setCategoryName(categoryDto.getCategoryName());
        Category savedCategory = categoryRepo.save(category);
        return this.modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public ApiResponse deleteCategory(int categoryId) throws ResourceNotFoundException {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        categoryRepo.delete(category);
      return new ApiResponse("Category is deleted",true);
    }

    @Override
    public Optional<Category> getCategoryById(int categoryId)  {
       return categoryRepo.findById(categoryId);

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
       List<CategoryDto> allDto = categories.stream().map(cat -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return allDto;
    }
}
