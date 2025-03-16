package org.example.radios.repository;

import jakarta.validation.constraints.Email;
import org.example.radios.model.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CheckOutRepo extends JpaRepository<CheckOut , UUID> {
    boolean existsByEmail(@Email String email);
}
