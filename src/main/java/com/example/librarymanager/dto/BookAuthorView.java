package com.example.librarymanager.dto;

public class BookAuthorView {

    private final Long bookId;
    private final String title;
    private final String genre;
    private final Integer publishedYear;
    private final String isbn;
    private final String authorName;
    private final String authorEmail;
    private final String country;

    public BookAuthorView(
            Long bookId,
            String title,
            String genre,
            Integer publishedYear,
            String isbn,
            String authorName,
            String authorEmail,
            String country) {
        this.bookId = bookId;
        this.title = title;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.country = country;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public String getCountry() {
        return country;
    }
}
