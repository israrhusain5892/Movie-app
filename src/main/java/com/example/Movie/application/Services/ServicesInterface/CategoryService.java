package com.example.Movie.application.Services.ServicesInterface;

import com.example.Movie.application.Dto.CategoryDto;
import com.example.Movie.application.Dto.MovieDto;

import java.util.List;

public interface CategoryService {

     CategoryDto addCategory(CategoryDto categoryDto);
     CategoryDto getCategoryById(Integer categoryId);
     CategoryDto updateCategoryById(CategoryDto categoryDto,Integer categoryId);

     String deleteCategoryById(Integer categoryId);
     List<CategoryDto> getAllCategory();
}
