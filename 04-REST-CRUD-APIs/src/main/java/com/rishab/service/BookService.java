package com.rishab.service;

import com.rishab.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();

    Book findBookById(Integer id);

    Book save(Book book);

    void deleteBook(Integer id);
}
