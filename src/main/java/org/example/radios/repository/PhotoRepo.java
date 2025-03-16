package org.example.radios.repository;

import org.example.radios.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoRepo extends JpaRepository<Photo , UUID> {
}
