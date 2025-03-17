package org.example.radios.controller;

import org.example.radios.dto.CheckOutDto;
import org.example.radios.model.CheckOut;
import org.example.radios.model.Result;
import org.example.radios.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/checkout")
public class CheckOutController {

    @Autowired
    CheckOutService checkOutService;

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN', 'USER')")
    public HttpEntity<?> readAll() {
        List<CheckOut> allCheckouts = checkOutService.getAllCheckouts();
        return new ResponseEntity<>(allCheckouts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN', 'USER')")
    public HttpEntity<?> readOne(@PathVariable UUID id) {
        CheckOut checkoutById = checkOutService.getCheckoutById(id);
        return new ResponseEntity<>(checkoutById, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    public HttpEntity<?> create(@RequestBody CheckOutDto checkoutDto) {
        Result result = checkOutService.create(checkoutDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    public HttpEntity<?> update(@PathVariable UUID id, @RequestBody CheckOutDto checkoutDto) {
        Result update = checkOutService.update(checkoutDto, id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    public HttpEntity<?> delete(@PathVariable UUID id) {
        Result result = checkOutService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
