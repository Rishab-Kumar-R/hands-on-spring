package com.rishab.service;

import com.rishab.dao.BookRepository;
import com.rishab.entity.Book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
