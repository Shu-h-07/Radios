package org.example.radios.repository;

import org.example.radios.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category , Integer> {
}
