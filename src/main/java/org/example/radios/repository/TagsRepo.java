package org.example.radios.repository;

import org.example.radios.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagsRepo  extends JpaRepository<Tags , UUID> {
}
