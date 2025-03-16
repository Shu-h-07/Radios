package org.example.radios.service;

import org.example.radios.dto.CardItemDto;
import org.example.radios.model.CardItem;
import org.example.radios.model.Product;
import org.example.radios.model.Result;
import org.example.radios.model.Total;
import org.example.radios.repository.CardItemRepo;
import org.example.radios.repository.ProductRepo;
import org.example.radios.repository.TotalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardItemService {

    @Autowired
    CardItemRepo cardItemRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    TotalRepo totalRepo;

    public List<CardItem> findAll() {
        return cardItemRepo.findAll();
    }

    public CardItem findById(UUID id) {
        return cardItemRepo.findById(id).get();
    }

    public Result create(CardItemDto cardItemDto) {
        CardItem cardItem = new CardItem();

        Optional<Total> byId = totalRepo.findById(cardItemDto.getSubTotal());
        Total total = byId.get();
        cardItem.setSubTotal(total);

        Optional<Product> byId1 = productRepo.findById(cardItemDto.getProductId());
        Product product = byId1.get();
        cardItem.setProductId((List<Product>) product);

        Optional<Product> byId2 = productRepo.findById(cardItemDto.getProductName());
        Product product2 = byId2.get();
        cardItem.setName(product2);

        Optional<Product> byId3 = productRepo.findById(cardItemDto.getProductPrice());
        Product product3 = byId3.get();
        cardItem.setPrice(product3);

        cardItemRepo.save(cardItem);
        return new Result(true , "created" );
    }

    public Result update(CardItemDto cardItemDto, UUID id) {
        Optional<CardItem> byId = cardItemRepo.findById(id);
        if (byId.isPresent()) {
            CardItem cardItem = byId.get();

            Optional<Total> byIdorg = totalRepo.findById(cardItemDto.getSubTotal());
            Total total = byIdorg.get();
            cardItem.setSubTotal(total);

            Optional<Product> byId1 = productRepo.findById(cardItemDto.getProductId());
            Product product = byId1.get();
            cardItem.setProductId((List<Product>) product);

            Optional<Product> byId2 = productRepo.findById(cardItemDto.getProductName());
            Product product2 = byId2.get();
            cardItem.setName(product2);

            Optional<Product> byId3 = productRepo.findById(cardItemDto.getProductPrice());
            Product product3 = byId3.get();
            cardItem.setPrice(product3);

            cardItemRepo.save(cardItem);
            return new Result(true ,"updated");
        }
        return new Result(false ,"not found" );
    }

    public Result delete(UUID id) {
        Optional<CardItem> byId = cardItemRepo.findById(id);
        if (byId.isPresent()) {
            cardItemRepo.delete(byId.get());
            return new Result(true ,"deleted");
        }
        return new Result(false ,"not found");
    }
}
