package org.example.radios.service;

import org.example.radios.dto.CategoryDto;
import org.example.radios.model.Category;
import org.example.radios.model.Result;
import org.example.radios.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo repo;

    public List<Category> findAll() {
        return repo.findAll();
    }

    public Category findById(UUID id) {
        return repo.findById(id).get();
    }

    public Category findByName(String name) {
        return repo.findByName(name).get();
    }

    public Result create(CategoryDto categoryDto) {
        boolean b = repo.existsByName(categoryDto.getName());
        if (b) {
            return new Result(false ," name is already used" );
        }
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        repo.save(category);
        return new Result(true ,"Category created " );
    }

    public Result update(CategoryDto categoryDto, UUID id) {
        Optional<Category> byId = repo.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            repo.save(category);
            return new Result(true ,"Category  updated " );
        }
        return new Result(false ,"Category not found" );
    }

    public Result delete(UUID id) {
        Optional<Category> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result(true ,"Category deleted " );
        }
        return new Result(false ,"Category not found" );
    }
}
