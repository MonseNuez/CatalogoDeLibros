package com.librocatalogo.service;

import com.librocatalogo.model.Book;
import com.librocatalogo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {
    @Autowired
    private BookRepository bookRepository;

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }
}
