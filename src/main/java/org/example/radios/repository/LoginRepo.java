package org.example.radios.repository;

import jakarta.validation.constraints.Email;
import org.example.radios.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<Login , Integer> {
    boolean existsByEmailAndUsername(@Email String email , String username);
}
