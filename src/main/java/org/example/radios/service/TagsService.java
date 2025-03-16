package org.example.radios.service;

import org.example.radios.dto.TagsDto;
import org.example.radios.model.Result;
import org.example.radios.model.Tags;
import org.example.radios.repository.TagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagsService {
    @Autowired
    TagsRepo repo;

    public List<Tags> getAllTags() {
        return repo.findAll();
    }

    public Tags getTagByID(UUID id) {
        return repo.findById(id).get();
    }

    public Result createT(TagsDto dto) {
        Tags tags = new Tags();
        tags.setName(dto.getName());
        repo.save(tags);
        return new Result(true , "Tags information created successfully" );
    }

    public Result updateT(TagsDto dto, UUID id) {
        Optional<Tags> byId = repo.findById(id);
        if (byId.isPresent()) {
            Tags tags = byId.get();
            tags.setName(dto.getName());
            repo.save(tags);
            return new Result(true , "Tags information updated successfully" );
        }
        return new Result(false ,"Tags information not found" );
    }

    public Result deleteT(UUID id) {
        Optional<Tags> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result(true ,"Tags information deleted successfully" );
        }
        return new Result(false,"Tags information not found");
    }

}
