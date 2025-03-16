package org.example.radios.service;

import org.example.radios.dto.PhotoDto;
import org.example.radios.model.Photo;
import org.example.radios.model.Result;
import org.example.radios.repository.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhotoService {

    @Autowired
    PhotoRepo photoRepo
            ;

    public List<Photo> getAllPhoto() {
        return photoRepo.findAll();
    }

    public Photo getByIdPhoto(UUID id) {
        return photoRepo.findById(id).get();
    }

    public Result createPh(PhotoDto dto) {
        Photo photo = new Photo();
        photo.setName(dto.getName());
        photo.setSize(dto.getSize());
        photo.setPath(dto.getPath());
        photoRepo.save(photo);
        return new Result(true ,"  created ");
    }

    public Result updatePh(PhotoDto dto, UUID id) {
        Optional<Photo> byId = photoRepo.findById(id);
        if (byId.isPresent()) {
            Photo photo = byId.get();
            photo.setName(dto.getName());
            photo.setSize(dto.getSize());
            photo.setPath(dto.getPath());
            photoRepo.save(photo);
            return new Result(true ,"  updated " );
        }
        return new Result(false ,"not found");
    }

    public Result deletePh(UUID id) {
        Optional<Photo> byId = photoRepo.findById(id);
        if (byId.isPresent()) {
            photoRepo.deleteById(id);
            return new Result(true ,"deleted ");
        }
        return new Result(false , "not found");
    }
}
