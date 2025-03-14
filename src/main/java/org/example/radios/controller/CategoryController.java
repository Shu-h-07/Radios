package org.example.radios.controller;

import org.example.radios.dto.CategoryDto;
import org.example.radios.model.Category;
import org.example.radios.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> getall(){
        List<Category> categories = categoryService.getAll();
        return new ResponseEntity<>(categories , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getbyid(@PathVariable Integer id){
        Category byId = categoryService.getById(id);
        return new ResponseEntity<>(byId , HttpStatus.OK);
    }
    @PostMapping
    public HttpEntity<?> create(@RequestBody CategoryDto categoryDto){

    }
}
