package com.example.librarymanager.repository;

import com.example.librarymanager.dto.BookAuthorView;
import com.example.librarymanager.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String isbn);

    @Override
    @EntityGraph(attributePaths = "author")
    Optional<Book> findById(Long id);

    @Query("""
            select new com.example.librarymanager.dto.BookAuthorView(
                b.id,
                b.title,
                b.genre,
                b.publishedYear,
                b.isbn,
                a.name,
                a.email,
                a.country
            )
            from Book b
            inner join b.author a
            order by b.title
            """)
    List<BookAuthorView> findBookAuthorDetails();
}
