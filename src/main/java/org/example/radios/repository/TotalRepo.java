package org.example.radios.repository;

import org.example.radios.model.Total;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TotalRepo extends JpaRepository<Total , UUID> {
}
