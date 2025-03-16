package org.example.radios.service;

import org.example.radios.dto.BlogDto;
import org.example.radios.model.Blog;
import org.example.radios.model.Comment;
import org.example.radios.model.Photo;
import org.example.radios.model.Result;
import org.example.radios.repository.BlogRepo;
import org.example.radios.repository.CommentRepo;
import org.example.radios.repository.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogService {
    @Autowired
    BlogRepo blogRepo;

    @Autowired
    PhotoRepo photoRepo;

    @Autowired
    CommentRepo commentRepo;

    public List<Blog> findAll() {
        return blogRepo.findAll();
    }

    public Blog findById(UUID id) {
        return blogRepo.findById(id).get();
    }

    public Result create(BlogDto dto) {
        Blog blog = new Blog();
        blog.setName(dto.getName());
        blog.setDescription(dto.getDescription());
        Optional<Photo> byId = photoRepo.findById(dto.getPhotoId());
        Photo photo = byId.get();
        blog.setPhotoId(photo);

        Optional<Comment> byId1 = commentRepo.findById(dto.getCommentId());
        Comment comment = byId1.get();
        blog.setCommentId(comment);

        blogRepo.save(blog);
        return new Result(true ,"created" );
    }

    public Result update(BlogDto dto, UUID id) {
        Optional<Blog> byId = blogRepo.findById(id);
        if (byId.isPresent()) {
            Blog blog = byId.get();
            blog.setName(dto.getName());
            blog.setDescription(dto.getDescription());

            Optional<Photo> byPhotoId = photoRepo.findById(dto.getPhotoId());
            Photo photo = byPhotoId.get();
            blog.setPhotoId(photo);

            Optional<Comment> byCommentId = commentRepo.findById(dto.getCommentId());
            Comment comment = byCommentId.get();
            blog.setCommentId(comment);

            blogRepo.save(blog);
            return new Result(true ,"updated" );
        }
        return new Result(false ,"not found" );
    }

    public Result delete(UUID id) {
        Optional<Blog> byId = blogRepo.findById(id);
        if (byId.isPresent()) {
            blogRepo.delete(byId.get());
            return new Result(true ,"deleted" );
        }
        return new Result(false ,"not found" );
    }
}
