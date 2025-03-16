package org.example.radios.repository;

import org.example.radios.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepo extends JpaRepository<Card , UUID> {
}
