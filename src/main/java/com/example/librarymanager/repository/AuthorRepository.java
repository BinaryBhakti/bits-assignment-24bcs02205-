package com.example.librarymanager.repository;

import com.example.librarymanager.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    boolean existsByEmail(String email);
}
