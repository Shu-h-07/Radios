package org.example.radios.repository;

import org.example.radios.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogRepo extends JpaRepository<Blog , UUID> {
}
