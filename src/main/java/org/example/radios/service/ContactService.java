package org.example.radios.service;

import org.example.radios.dto.ContactDto;
import org.example.radios.model.Contact;
import org.example.radios.model.Result;
import org.example.radios.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactService {

    @Autowired
    ContactRepo contactRepo;

    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    public Contact getById(UUID id) {
        return contactRepo.findById(id).get();
    }

    public Result create(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setText(contactDto.getText());
        contact.setPhoneNumber(contactDto.getPhoneNumber());
        contactRepo.save(contact);
        return new Result(true ,"Contact created" );
    }

    public Result update(ContactDto contactDto, UUID id) {
        Optional<Contact> byId = contactRepo.findById(id);
        if (byId.isPresent()) {
            Contact contact = byId.get();
            contact.setText(contactDto.getText());
            contact.setPhoneNumber(contactDto.getPhoneNumber());
            contactRepo.save(contact);
            return new Result(true ,"Contact updated");
        }
        return new Result(false ,"Contact not found" );
    }

    public Result delete(UUID id) {
        Optional<Contact> byId = contactRepo.findById(id);
        if (byId.isPresent()) {
            contactRepo.delete(byId.get());
            return new Result(true ,"Contact deleted" );
        }
        return new Result(false ,"Contact not found");
    }
}
