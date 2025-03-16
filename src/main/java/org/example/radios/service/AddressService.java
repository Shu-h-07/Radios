package org.example.radios.service;

import org.example.radios.dto.AddressDto;
import org.example.radios.model.Address;
import org.example.radios.model.Result;
import org.example.radios.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    AddressRepo addressRepo;

    public List<Address> getAllAddresses() {
        return addressRepo.findAll();
    }

    public Address getAddressById(UUID id) {
        return addressRepo.findById(id).get();
    }

    public Result create(AddressDto addressDto) {
        Address address = new Address();
        address.setCountry(addressDto.getCountry());
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setZipCode(addressDto.getZipCode());
        addressRepo.save(address);
        return new Result(true ,"Address  created" );
    }

    public Result update(AddressDto dto, UUID id) {
        Optional<Address> byId = addressRepo.findById(id);
        if (byId.isPresent()) {
            Address address = byId.get();
            address.setCountry(dto.getCountry());
            address.setCity(dto.getCity());
            address.setStreet(dto.getStreet());
            address.setZipCode(dto.getZipCode());
            addressRepo.save(address);
            return new Result(true ,"Address  updated" );
        }
        return new Result(false ,"Address not found" );
    }

    public Result delete(UUID id) {
        Optional<Address> byId = addressRepo.findById(id);
        if (byId.isPresent()) {
            addressRepo.deleteById(id);
            return new Result(true ,"Address deleted ");
        }
        return new Result(false ,"Address not found" );
    }
}
