package org.example.radios.service;

import org.example.radios.dto.CommentDto;
import org.example.radios.model.Comment;
import org.example.radios.model.Result;
import org.example.radios.repository.CommentRepo;
import org.example.radios.repository.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    LikeRepo likeRepo;

    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    public Comment getCommentById(UUID id) {
        return commentRepo.findById(id).get();
    }

    public Result createComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setDescription(commentDto.getDescription());
        comment.setLike(likeRepo.findById(commentDto.getLikeId()).get());
        commentRepo.save(comment);
        return new Result(true , "  created and saved " );
    }

    public Result updateComment(UUID id, CommentDto commentDto) {
        Optional<Comment> byId = commentRepo.findById(id);
        if (byId.isPresent()) {
            Comment comment = byId.get();
            comment.setText(commentDto.getText());
            comment.setDescription(commentDto.getDescription());
            comment.setLike(likeRepo.findById(commentDto.getLikeId()).get());
            commentRepo.save(comment);
            return new Result(true ,"  updated and saved ");
        }
        return new Result(false , "  not found" );
    }

    public Result deleteComment(UUID id) {
        Optional<Comment> byId = commentRepo.findById(id);
        if (byId.isPresent()) {
            commentRepo.delete(byId.get());
            return new Result(true , "  deleted and saved ");
        }
        return new Result(false ,"not found" );
    }
}
