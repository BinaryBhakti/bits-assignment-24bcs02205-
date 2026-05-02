package com.example.librarymanager.config;

import com.example.librarymanager.entity.Author;
import com.example.librarymanager.entity.Book;
import com.example.librarymanager.repository.AuthorRepository;
import com.example.librarymanager.repository.BookRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataSeeder(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        if (authorRepository.count() > 0 || bookRepository.count() > 0) {
            return;
        }

        List<Author> authors = authorRepository.saveAll(List.of(
                new Author("Aarav Mehta", "aarav.mehta@example.com", "India", 1978),
                new Author("Maya Stone", "maya.stone@example.com", "United States", 1984),
                new Author("Lena Ortiz", "lena.ortiz@example.com", "Spain", 1971),
                new Author("Noah Reed", "noah.reed@example.com", "Canada", 1969),
                new Author("Isha Nair", "isha.nair@example.com", "India", 1990),
                new Author("Ethan Brooks", "ethan.brooks@example.com", "United Kingdom", 1982),
                new Author("Sofia Rossi", "sofia.rossi@example.com", "Italy", 1976),
                new Author("Kenji Tanaka", "kenji.tanaka@example.com", "Japan", 1965),
                new Author("Amara Okafor", "amara.okafor@example.com", "Nigeria", 1988),
                new Author("Clara Jensen", "clara.jensen@example.com", "Denmark", 1974)
        ));

        bookRepository.saveAll(List.of(
                new Book("Spring in Practice", "Technology", 2020, "9781000000001", authors.get(0)),
                new Book("Cloud Atlas Notes", "Science Fiction", 2019, "9781000000002", authors.get(1)),
                new Book("The Quiet Harbor", "Literary Fiction", 2017, "9781000000003", authors.get(2)),
                new Book("Northern Trails", "Adventure", 2015, "9781000000004", authors.get(3)),
                new Book("Data Garden", "Technology", 2022, "9781000000005", authors.get(4)),
                new Book("London Rain", "Mystery", 2018, "9781000000006", authors.get(5)),
                new Book("Olive Tree Summer", "Romance", 2014, "9781000000007", authors.get(6)),
                new Book("Kyoto Signals", "Thriller", 2021, "9781000000008", authors.get(7)),
                new Book("Lagos Morning", "Historical Fiction", 2016, "9781000000009", authors.get(8)),
                new Book("Nordic Light", "Poetry", 2023, "9781000000010", authors.get(9))
        ));
    }
}
