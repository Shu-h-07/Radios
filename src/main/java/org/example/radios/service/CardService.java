package org.example.radios.service;

import org.example.radios.dto.CardDto;
import org.example.radios.model.Card;
import org.example.radios.model.Result;
import org.example.radios.repository.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {

    @Autowired
    CardRepo cardRepo;

    public List<Card> findAll() {
        return cardRepo.findAll();
    }

    public Card findById(UUID id) {
        return cardRepo.findById(id).get();
    }

    public Result create(CardDto cardDto) {
        Card card = new Card();
        card.setCardNumber(cardDto.getCardNumber());
        card.setExpiryDate(cardDto.getExpiryDate());
        card.setCvvNumber(cardDto.getCvvNumber());
        cardRepo.save(card);
        return new Result(true , "created and saved");
    }

    public Result update(CardDto cardDto, UUID id) {
        Optional<Card> byId = cardRepo.findById(id);
        if (byId.isPresent()) {
            Card card = byId.get();
            card.setCardNumber(cardDto.getCardNumber());
            card.setExpiryDate(cardDto.getExpiryDate());
            card.setCvvNumber(cardDto.getCvvNumber());
            cardRepo.save(card);
            return new Result(true ,"updated" );
        }
        return new Result(false , "not found" );
    }

    public Result delete(UUID id) {
        Optional<Card> byId = cardRepo.findById(id);
        if (byId.isPresent()) {
            cardRepo.delete(byId.get());
            return new Result(true ,"deleted");
        }
        return new Result(false ,"not found" );
    }
}
