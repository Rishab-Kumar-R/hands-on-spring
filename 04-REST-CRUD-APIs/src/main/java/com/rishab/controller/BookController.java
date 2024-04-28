package com.rishab.controller;

import com.rishab.entity.Book;
import com.rishab.service.BookService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book findBookById(@PathVariable Integer id) {
        return bookService.findBookById(id);
    }

    @PostMapping("/books")
    public Book save(@RequestBody Book book) {
        book.setId(0);
        return bookService.save(book);
    }

    @PutMapping("/books")
    public Book update(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable Integer id) {
        Book book = bookService.findBookById(id);

        if (book == null) {
            throw new RuntimeException("Book id not found - " + id);
        }

        bookService.deleteBook(id);
        return "Book with id " + id + " deleted successfully";
    }
}
