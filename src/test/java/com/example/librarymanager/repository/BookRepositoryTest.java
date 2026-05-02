package com.example.librarymanager.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.librarymanager.dto.BookAuthorView;
import com.example.librarymanager.entity.Author;
import com.example.librarymanager.entity.Book;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findBookAuthorDetailsReturnsInnerJoinProjection() {
        Author author = authorRepository.save(new Author("Test Author", "author@test.com", "India", 1985));
        bookRepository.save(new Book("Repository Testing", "Technology", 2024, "9782000000001", author));

        List<BookAuthorView> results = bookRepository.findBookAuthorDetails();

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getTitle()).isEqualTo("Repository Testing");
        assertThat(results.get(0).getAuthorName()).isEqualTo("Test Author");
    }
}
