package com.example.librarymanager.controller;

import com.example.librarymanager.entity.Author;
import com.example.librarymanager.service.AuthorService;
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
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/list";
    }

    @GetMapping("/authors/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("pageTitle", "Add Author");
        model.addAttribute("formAction", "/authors");
        return "authors/form";
    }

    @PostMapping("/authors")
    public String createAuthor(
            @Valid @ModelAttribute("author") Author author,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Add Author");
            model.addAttribute("formAction", "/authors");
            return "authors/form";
        }
        try {
            authorService.save(author);
            redirectAttributes.addFlashAttribute("successMessage", "Author saved successfully.");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException ex) {
            bindingResult.reject("duplicate", "Author name and email must be unique.");
            model.addAttribute("pageTitle", "Add Author");
            model.addAttribute("formAction", "/authors");
            return "authors/form";
        }
    }

    @GetMapping("/authors/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("author", authorService.findById(id));
            model.addAttribute("pageTitle", "Edit Author");
            model.addAttribute("formAction", "/authors/" + id);
            return "authors/form";
        } catch (EntityNotFoundException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/authors";
        }
    }

    @PostMapping("/authors/{id}")
    public String updateAuthor(
            @PathVariable Long id,
            @Valid @ModelAttribute("author") Author author,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Edit Author");
            model.addAttribute("formAction", "/authors/" + id);
            return "authors/form";
        }
        try {
            authorService.update(id, author);
            redirectAttributes.addFlashAttribute("successMessage", "Author updated successfully.");
            return "redirect:/authors";
        } catch (EntityNotFoundException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/authors";
        } catch (DataIntegrityViolationException ex) {
            bindingResult.reject("duplicate", "Author name and email must be unique.");
            model.addAttribute("pageTitle", "Edit Author");
            model.addAttribute("formAction", "/authors/" + id);
            return "authors/form";
        }
    }
}
