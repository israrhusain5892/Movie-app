package com.example.Movie.application.Services.ServicesImpl;

import com.example.Movie.application.Dto.CategoryDto;
import com.example.Movie.application.Exceptions.ResourceNotFoundException;
import com.example.Movie.application.Models.Category;
import com.example.Movie.application.Repository.CategoryRepo;
import com.example.Movie.application.Services.ServicesInterface.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category cat=modelMapper.map(categoryDto,Category.class);
        Category savedCategory=categoryRepo.save(cat);
        return  modelMapper.map(cat,CategoryDto.class);

    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category= categoryRepo.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
           return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategoryById(CategoryDto categoryDto,Integer categoryId) {

        Category category= categoryRepo.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
             category.setCategory_name(categoryDto.getCategory_name());
              Category savedCategory=categoryRepo.save(category);
            return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public String deleteCategoryById(Integer categoryId) {

        Category category= categoryRepo.findById(categoryId).
                orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
            categoryRepo.deleteById(categoryId);
            return  "category "+ category.getCategory_name()+" has been deleted successfully !!";
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> category= categoryRepo.findAll();
        List<CategoryDto> list=new ArrayList<>();
        for(Category cat:category){
             CategoryDto categoryDto=modelMapper.map(cat,CategoryDto.class);
             list.add(categoryDto);
        }
        return list;

    }


}
