package com.example.librarymanager.controller;

import com.example.librarymanager.entity.Book;
import com.example.librarymanager.service.AuthorService;
import com.example.librarymanager.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("bookAuthorDetails", bookService.findBookAuthorDetails());
        return "books/list";
    }

    @GetMapping("/books/new")
    public String showCreateForm(Model model) {
        prepareBookForm(model, new Book(), "Add Book", "/books");
        return "books/form";
    }

    @PostMapping("/books")
    public String createBook(
            @Valid @ModelAttribute("book") Book book,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareBookForm(model, book, "Add Book", "/books");
            return "books/form";
        }
        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            bindingResult.rejectValue("author", "required", "Author is required");
            prepareBookForm(model, book, "Add Book", "/books");
            return "books/form";
        }
        try {
            bookService.save(book);
            redirectAttributes.addFlashAttribute("successMessage", "Book saved successfully.");
            return "redirect:/books";
        } catch (DataIntegrityViolationException ex) {
            bindingResult.reject("duplicate", "Book title and ISBN must be unique.");
            prepareBookForm(model, book, "Add Book", "/books");
            return "books/form";
        } catch (EntityNotFoundException ex) {
            bindingResult.rejectValue("author", "missing", ex.getMessage());
            prepareBookForm(model, book, "Add Book", "/books");
            return "books/form";
        }
    }

    @GetMapping("/books/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            prepareBookForm(model, bookService.findById(id), "Edit Book", "/books/" + id);
            return "books/form";
        } catch (EntityNotFoundException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/books";
        }
    }

    @PostMapping("/books/{id}")
    public String updateBook(
            @PathVariable Long id,
            @Valid @ModelAttribute("book") Book book,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareBookForm(model, book, "Edit Book", "/books/" + id);
            return "books/form";
        }
        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            bindingResult.rejectValue("author", "required", "Author is required");
            prepareBookForm(model, book, "Edit Book", "/books/" + id);
            return "books/form";
        }
        try {
            bookService.update(id, book);
            redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully.");
            return "redirect:/books";
        } catch (IllegalArgumentException ex) {
            bindingResult.rejectValue("author", "required", ex.getMessage());
            prepareBookForm(model, book, "Edit Book", "/books/" + id);
            return "books/form";
        } catch (DataIntegrityViolationException ex) {
            bindingResult.reject("duplicate", "Book title and ISBN must be unique.");
            prepareBookForm(model, book, "Edit Book", "/books/" + id);
            return "books/form";
        } catch (EntityNotFoundException ex) {
            bindingResult.rejectValue("author", "missing", ex.getMessage());
            prepareBookForm(model, book, "Edit Book", "/books/" + id);
            return "books/form";
        }
    }

    private void prepareBookForm(Model model, Book book, String pageTitle, String formAction) {
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("formAction", formAction);
    }
}
