package org.example.radios.service;

import org.example.radios.dto.LoginDto;
import org.example.radios.model.Login;
import org.example.radios.model.Result;
import org.example.radios.repository.LoginRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    LoginRepo loginRepo;

    public List<Login> getAll(){
        return loginRepo.findAll();
    }
    public Login getById(Integer id){
        return loginRepo.findById(id).get();
    }
    public Result create(LoginDto loginDto){
        boolean exists = loginRepo.existsByEmailAndUsername(loginDto.getEmail(), loginDto.getUsername());

        if (exists){
            return new Result(false, "Email already exists");
        }
        Login login = new Login();
        login.setEmail(loginDto.getEmail());
        login.setUsername(loginDto.getUsername());
        login.setPassword(loginDto.getPassword());
        loginRepo.save(login);
        return new Result(true , "Saqlandi");
    }
    public Result update(LoginDto loginDto , Integer id){
        Optional<Login> byId = loginRepo.findById(id);
        if (byId.isPresent()){
            Login login = byId.get();
            login.setEmail(loginDto.getEmail());
            login.setUsername(loginDto.getUsername());
            login.setPassword(loginDto.getPassword());
            loginRepo.save(login);
            return new Result(true , "Ozgartirildi");
        }
        return new Result(false , "Bomadi");
    }
    public Result delete(Integer id){
        Optional<Login> loginOptional = loginRepo.findById(id);
        if (loginOptional.isPresent()) {
            loginRepo.deleteById(id);
            return new Result(true, "Udalit");
        }
        return new Result(false , "Not found");
    }
}
