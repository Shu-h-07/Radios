package org.example.radios.service;

import org.example.radios.dto.LanguageDto;
import org.example.radios.model.Language;
import org.example.radios.model.Result;
import org.example.radios.repository.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LanguageService {
    @Autowired
    LanguageRepo languageRepo;

    public List<Language> findAll() {
        return languageRepo.findAll();
    }

    public Language findById(UUID id) {
        return languageRepo.findById(id).get();
    }

    public Language findByName(String name) {
        return languageRepo.findByName(name).get();
    }

    public Result create(LanguageDto dto) {
        Language language = new Language();
        language.setName(dto.getName());
        language.setUz(dto.getUz());
        language.setRu(dto.getRu());
        language.setEng(dto.getEng());
        languageRepo.save(language);
        return new Result(true ,"Language created" );
    }

    public Result update(LanguageDto dto, UUID id) {
        Optional<Language> byId = languageRepo.findById(id);
        if (byId.isPresent()) {
            Language language = byId.get();
            language.setName(dto.getName());
            language.setUz(dto.getUz());
            language.setRu(dto.getRu());
            language.setEng(dto.getEng());
            languageRepo.save(language);
            return new Result(true ,"Language updated" );
        }
        return new Result(false ,"Language not found" );
    }

    public Result delete(UUID id) {
        Optional<Language> byId = languageRepo.findById(id);
        if (byId.isPresent()) {
            languageRepo.delete(byId.get());
            return new Result(true ,"Language deleted" );
        }
        return new Result(false ,"Language not found" );
    }
}
