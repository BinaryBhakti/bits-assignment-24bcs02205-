# Library Manager Project Report

## Entity Relationship Design

The application manages two entities: Author and Book. One author can write many books, while every book belongs to exactly one author.

- `Author`: `id`, `name`, `email`, `country`, `birthYear`
- `Book`: `id`, `title`, `genre`, `publishedYear`, `isbn`, `author`
- Relationship: `Author @OneToMany Book`, `Book @ManyToOne Author`

JPA creates two tables: `authors` and `books`. The `books` table contains the foreign key `author_id`.

## Populate Database

The `DataSeeder` class implements `CommandLineRunner`. At application startup, it inserts 10 authors and 10 books if the database is empty. Hibernate creates the tables automatically using the entity annotations and `spring.jpa.hibernate.ddl-auto=create`.

## Create Operation

Create forms are implemented in JSP:

- `src/main/webapp/WEB-INF/jsp/authors/form.jsp`
- `src/main/webapp/WEB-INF/jsp/books/form.jsp`

Controllers handle form submission through `POST /authors` and `POST /books`. The submitted objects are validated using Jakarta Bean Validation annotations. `DataIntegrityViolationException` is caught to show friendly duplicate-value errors for unique fields such as author email, book title, and ISBN.

## Read Operation

List pages are implemented in JSP:

- `src/main/webapp/WEB-INF/jsp/authors/list.jsp`
- `src/main/webapp/WEB-INF/jsp/books/list.jsp`

The book list uses the repository method `findBookAuthorDetails()`, which performs an inner join between `Book` and `Author` and returns a DTO projection named `BookAuthorView`.

## Update Operation

Edit links are available on the list pages. The update forms reuse the same JSP form files used for creation. Controllers load the existing record with `GET /authors/{id}/edit` or `GET /books/{id}/edit`, then persist changes with `POST /authors/{id}` or `POST /books/{id}`.

## Testing

Repository testing uses `@DataJpaTest` to verify the custom inner join query. Service testing uses JUnit 5 and Mockito to verify that business logic delegates correctly to repositories and resolves a managed author before saving a book.

Run tests with:

```bash
mvn test
```

## Screenshots

Add screenshots after running the application:

- Books list page: `http://localhost:8080/books`
- Add book page: `http://localhost:8080/books/new`
- Authors list page: `http://localhost:8080/authors`
- Add author page: `http://localhost:8080/authors/new`

## Challenges Faced

The main challenge was keeping JSP form binding clean for a relationship field. The book form binds the selected author through `author.id`; the service then loads the managed `Author` entity before saving the `Book`. This avoids detached-entity issues and keeps the controller small.

## GitHub URL

`https://github.com/BinaryBhakti/bits-assignment-24bcs02205-.git`
