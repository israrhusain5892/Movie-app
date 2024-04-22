package com.example.Movie.application.controller;

import com.example.Movie.application.Dto.CategoryDto;
import com.example.Movie.application.Services.ServicesImpl.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;
    @PostMapping("/")
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto){
           return categoryServiceImp.addCategory(categoryDto);
    }

    @GetMapping("/{categoryId}")
    public CategoryDto findCategoryById(@PathVariable Integer categoryId){
           return categoryServiceImp.getCategoryById(categoryId);
    }

    @PutMapping("/{categoryId}")
    public CategoryDto updateCategory( @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
          return categoryServiceImp.updateCategoryById(categoryDto,categoryId);
    }
    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable Integer categoryId){
         return categoryServiceImp.deleteCategoryById(categoryId);
    }

    @GetMapping("/")
    public List<CategoryDto> getAllCategory(){
         return categoryServiceImp.getAllCategory();
    }
}
