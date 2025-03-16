package org.example.radios.service;

import org.example.radios.dto.LikeDto;
import org.example.radios.model.Like;
import org.example.radios.model.Result;
import org.example.radios.repository.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LikeService {

    @Autowired
    LikeRepo likeRepo;

    public List<Like> getLikes() {
        return likeRepo.findAll();
    }

    public Like getLike(UUID id) {
        return likeRepo.findById(id).get();
    }

    public Result createLike(LikeDto likeDto) {
        Like like = new Like();
        like.setName(likeDto.getName());
        like.setCount(likeDto.getCount());
        likeRepo.save(like);
        return new Result(true ,"created");
    }

    public Result updateLike(UUID id, LikeDto likeDto) {
        Optional<Like> byId = likeRepo.findById(id);
        if (byId.isPresent()) {
            Like like = byId.get();
            like.setName(likeDto.getName());
            like.setCount(likeDto.getCount());
            likeRepo.save(like);
            return new Result(true ,"updated" );
        }
        return new Result(false ,"not found" );
    }

    public Result deleteLike(UUID id) {
        Optional<Like> byId = likeRepo.findById(id);
        if (byId.isPresent()) {
            likeRepo.delete(byId.get());
            return new Result(true , "deleted" );
        }
        return new Result(false ,"not found" );
    }
}
