package com.example.librarymanager.service;

import com.example.librarymanager.entity.Author;
import com.example.librarymanager.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Author update(Long id, Author formAuthor) {
        Author existingAuthor = findById(id);
        existingAuthor.setName(formAuthor.getName());
        existingAuthor.setEmail(formAuthor.getEmail());
        existingAuthor.setCountry(formAuthor.getCountry());
        existingAuthor.setBirthYear(formAuthor.getBirthYear());
        return authorRepository.save(existingAuthor);
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id " + id));
    }
}
