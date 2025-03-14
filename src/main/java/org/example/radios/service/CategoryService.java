package org.example.radios.service;

import org.example.radios.dto.CategoryDto;
import org.example.radios.model.Category;
import org.example.radios.model.Result;
import org.example.radios.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> getAll(){
        return categoryRepo.findAll();
    }
    public Category getById(Integer id){
        return categoryRepo.getById(id);
    }
    public Result createC(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepo.save(category);
        return new Result(true, "Add qilindi");
    }
    public Result updateC(Integer id , CategoryDto categoryDto){
        Optional<Category> optionalCategory
                = categoryRepo.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            categoryRepo.save(category);
            return new Result(true, "Update qilindi");
        }
        return new Result(false, " Qilinmadi");
    }
    public Result deleteC(Integer id){
        categoryRepo.deleteById(id);
        return new Result(true, "Delete qilindi");
    }
}
