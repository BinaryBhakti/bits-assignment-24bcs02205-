package com.example.librarymanager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.librarymanager.entity.Author;
import com.example.librarymanager.repository.AuthorRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void updateChangesManagedAuthorFields() {
        Author existingAuthor = new Author("Old Name", "old@test.com", "India", 1980);
        existingAuthor.setId(7L);
        Author formAuthor = new Author("New Name", "new@test.com", "Canada", 1990);

        when(authorRepository.findById(7L)).thenReturn(Optional.of(existingAuthor));
        when(authorRepository.save(existingAuthor)).thenReturn(existingAuthor);

        Author updatedAuthor = authorService.update(7L, formAuthor);

        assertThat(updatedAuthor.getName()).isEqualTo("New Name");
        assertThat(updatedAuthor.getEmail()).isEqualTo("new@test.com");
        assertThat(updatedAuthor.getCountry()).isEqualTo("Canada");
        assertThat(updatedAuthor.getBirthYear()).isEqualTo(1990);
        verify(authorRepository).save(existingAuthor);
    }
}
