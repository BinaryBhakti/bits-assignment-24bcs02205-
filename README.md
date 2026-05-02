# Library Manager

A Spring Boot MVC application for managing two related entities: `Author` and `Book`.

## Features

- JPA entities with a one-to-many relationship from authors to books.
- H2 in-memory database with 10 authors and 10 books inserted at startup.
- Create, read, and update flows for both entities.
- JSP views using JSTL and Spring form tags.
- Repository custom query using an inner join between books and authors.
- Unit tests for repository and service behavior.

## Run

Install Maven, then run:

```bash
mvn spring-boot:run
```

Open:

- App: `http://localhost:8080/books`
- Authors: `http://localhost:8080/authors`
- H2 console: `http://localhost:8080/h2-console`

H2 JDBC URL: `jdbc:h2:mem:librarydb`

## Test

```bash
mvn test
```

## GitHub URL

Replace this placeholder with your submitted repository URL:

`https://github.com/BinaryBhakti/bits-assignment-24bcs02205-.git`
