package com.example.librarymanager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.librarymanager.entity.Author;
import com.example.librarymanager.entity.Book;
import com.example.librarymanager.repository.BookRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookService bookService;

    @Test
    void saveLoadsManagedAuthorBeforeSavingBook() {
        Author formAuthor = new Author();
        formAuthor.setId(10L);
        Author managedAuthor = new Author("Managed Author", "managed@test.com", "India", 1980);
        managedAuthor.setId(10L);
        Book book = new Book("Service Testing", "Technology", 2024, "9783000000001", formAuthor);

        when(authorService.findById(10L)).thenReturn(managedAuthor);
        when(bookRepository.save(book)).thenReturn(book);

        Book saved = bookService.save(book);

        assertThat(saved.getAuthor()).isSameAs(managedAuthor);
        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captor.capture());
        assertThat(captor.getValue().getAuthor()).isSameAs(managedAuthor);
    }

    @Test
    void findAllDelegatesToRepository() {
        when(bookRepository.findAll()).thenReturn(List.of(new Book()));

        assertThat(bookService.findAll()).hasSize(1);

        verify(bookRepository).findAll();
    }
}
