package org.example.radios.repository;

import org.example.radios.model.About;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AboutRepo extends JpaRepository<About  , UUID> {
}
