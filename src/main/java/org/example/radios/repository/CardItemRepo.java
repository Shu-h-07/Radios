package org.example.radios.repository;

import org.example.radios.model.CardItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardItemRepo extends JpaRepository<CardItem , UUID> {
}
