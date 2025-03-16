package org.example.radios.repository;

import org.example.radios.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikeRepo extends JpaRepository<Like , UUID> {
}
