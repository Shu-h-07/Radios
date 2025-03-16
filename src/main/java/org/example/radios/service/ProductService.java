package org.example.radios.service;

import org.example.radios.dto.ProductDto;
import org.example.radios.model.Product;
import org.example.radios.model.Result;
import org.example.radios.repository.CategoryRepo;
import org.example.radios.repository.ProductRepo;
import org.example.radios.repository.TagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    TagsRepo tagsRepo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProduct(UUID id) {
        return repo.findById(id).get();
    }

    public Product getProductByName(String name) {
        return repo.findByName(name).get();
    }

    public Result createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setRate(productDto.getRate());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setModel(productDto.getModel());
        product.setCount(productDto.getCount());
        repo.save(product);
        return new Result( true,"Product information created successfully");
    }

    public Result updateProduct(UUID id, ProductDto productDto) {
        Optional<Product> byId = repo.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            product.setName(productDto.getName());
            product.setRate(productDto.getRate());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setModel(productDto.getModel());
            product.setCount(productDto.getCount());
            repo.save(product);
            return new Result(true , "   successfully" );
        }
        return new Result(false , "  not found");
    }

    public Result deleteProduct(UUID id) {
        Optional<Product> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result(true , "  deleted successfully" );
        }
        return new Result(false ,"  not found");
    }
}
