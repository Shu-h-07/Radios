package org.example.radios.service;

import org.example.radios.dto.CheckOutDto;
import org.example.radios.model.CheckOut;
import org.example.radios.model.Result;
import org.example.radios.repository.CheckOutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CheckOutService {
    @Autowired
    CheckOutRepo checkOutRepo;

    public List<CheckOut> getAllCheckouts() {
        return checkOutRepo.findAll();
    }

    public CheckOut getCheckoutById(UUID id) {
        return checkOutRepo.findById(id).get();
    }

    public Result create(CheckOutDto checkOutDto) {
        CheckOut checkout = new CheckOut();
        checkout.setFirstName(checkOutDto.getFirstName());
        checkout.setLastName(checkOutDto.getLastName());
        checkout.setEmail(checkOutDto.getEmail());
        checkout.setPhone(checkOutDto.getPhone());
        checkout.setPassword(checkOutDto.getPassword());
        checkout.setCompanyName(checkOutDto.getCompanyName());
        checkOutRepo.save(checkout);
        return new Result(true ,"created" );
    }

    public Result update(CheckOutDto checkOutDto, UUID id) {
        Optional<CheckOut> byId = checkOutRepo.findById(id);
        if (byId.isPresent()) {
            CheckOut checkout = byId.get();
            checkout.setFirstName(checkOutDto.getFirstName());
            checkout.setLastName(checkOutDto.getLastName());
            checkout.setEmail(checkOutDto.getEmail());
            checkout.setPhone(checkOutDto.getPhone());
            checkout.setPassword(checkOutDto.getPassword());
            checkout.setCompanyName(checkOutDto.getCompanyName());
            checkOutRepo.save(checkout);
            return new Result(true ,"updated" );
        }
        return new Result(false ,"Checkout not found" );
    }

    public Result delete(UUID id) {
        Optional<CheckOut> byId = checkOutRepo.findById(id);
        if (byId.isPresent()) {
            checkOutRepo.delete(byId.get());
            return new Result(true ,"Checkout deleted");
        }
        return new Result(false ,"Checkout not found");
    }
}
