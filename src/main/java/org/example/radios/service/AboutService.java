package org.example.radios.service;

import org.example.radios.dto.AboutDto;
import org.example.radios.model.About;
import org.example.radios.model.Result;
import org.example.radios.repository.AboutRepo;
import org.example.radios.repository.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AboutService {
    @Autowired
    AboutRepo aboutRepo;

    @Autowired
    PhotoRepo photoRepo;

    public List<About> getAllAbout() {
        return aboutRepo.findAll();
    }

    public About getAboutById(UUID id) {
        return aboutRepo.findById(id).get();
    }

    public Result create(AboutDto aboutDto) {
        About about = new About();
        about.setName(aboutDto.getName());
        about.setDescription(aboutDto.getDescription());
        about.setPhoto(photoRepo.findById(aboutDto.getPhotoId()).get());
        aboutRepo.save(about);
        return new Result(true ,"created" );
    }

    public Result update(AboutDto dto, UUID id) {
        Optional<About> byId = aboutRepo.findById(id);
        if (byId.isPresent()) {
            About about = byId.get();
            about.setName(dto.getName());
            about.setDescription(dto.getDescription());
            about.setPhoto(photoRepo.findById(dto.getPhotoId()).get());
            aboutRepo.save(about);
            return new Result(true ,"updated" );
        }
        return new Result(false ,"not found");
    }

    public Result delete(UUID id) {
        Optional<About> byId = aboutRepo.findById(id);
        if (byId.isPresent()) {
            aboutRepo.delete(byId.get());
            return new Result(true ,"deleted" );
        }
        return new Result(false ,"not found" );
    }
}
