package org.example.radios.controller;

import org.example.radios.dto.LoginDto;
import org.example.radios.model.Login;
import org.example.radios.model.Result;
import org.example.radios.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping
    public HttpEntity<?> all(){
        List<Login> all = loginService.getAll();
        return new ResponseEntity<>(all , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getbyid(@PathVariable Integer id){
        Login byId = loginService.getById(id);
        return new ResponseEntity<>(byId , HttpStatus.OK);
    }
    @PostMapping
    public Result create(@RequestBody LoginDto loginDto){
        Result result = loginService.create(loginDto);
        return result;
    }
    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable Integer id, @RequestBody LoginDto loginDto){
        Result update = loginService.update(loginDto, id);
        return new ResponseEntity<>(update , HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        Result result = loginService.delete(id);
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
