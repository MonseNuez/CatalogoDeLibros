package com.librocatalogo.controller;

import com.librocatalogo.model.Book;
import com.librocatalogo.service.DatabaseService;
import com.librocatalogo.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private GutendexService gutendexService;

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String title) throws IOException {
        List<Book> books = gutendexService.searchBooksByTitle(title);
        for (Book book : books) {
            databaseService.saveBook(book);
        }
        return books;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return databaseService.getAllBooks();
    }

    @GetMapping("/language")
    public List<Book> getBooksByLanguage(@RequestParam String language) {
        return databaseService.findByLanguage(language);
    }
}
