package com.example.librarymanager.service;

import com.example.librarymanager.dto.BookAuthorView;
import com.example.librarymanager.entity.Author;
import com.example.librarymanager.entity.Book;
import com.example.librarymanager.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public Book save(Book book) {
        Long authorId = book.getAuthor() == null ? null : book.getAuthor().getId();
        if (authorId == null) {
            throw new IllegalArgumentException("Author is required");
        }
        Author managedAuthor = authorService.findById(authorId);
        book.setAuthor(managedAuthor);
        return bookRepository.save(book);
    }

    public Book update(Long id, Book formBook) {
        Book existingBook = findById(id);
        Long authorId = formBook.getAuthor() == null ? null : formBook.getAuthor().getId();
        if (authorId == null) {
            throw new IllegalArgumentException("Author is required");
        }
        Author managedAuthor = authorService.findById(authorId);
        existingBook.setTitle(formBook.getTitle());
        existingBook.setGenre(formBook.getGenre());
        existingBook.setPublishedYear(formBook.getPublishedYear());
        existingBook.setIsbn(formBook.getIsbn());
        existingBook.setAuthor(managedAuthor);
        return bookRepository.save(existingBook);
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + id));
    }

    @Transactional(readOnly = true)
    public List<BookAuthorView> findBookAuthorDetails() {
        return bookRepository.findBookAuthorDetails();
    }
}
