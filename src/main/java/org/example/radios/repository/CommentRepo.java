package org.example.radios.repository;

import org.example.radios.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepo extends JpaRepository<Comment , UUID> {
}
