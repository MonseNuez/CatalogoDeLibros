package com.librocatalogo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librocatalogo.config.ConsumoApiGutendex;
import com.librocatalogo.model.Book;
import com.librocatalogo.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {

    @Autowired
    private ConsumoApiGutendex consumoApiGutendex;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Book> searchBooksByTitle(String title) throws IOException {
        String url = "https://gutendex.com/books?search=" + title;
        String response = consumoApiGutendex.obtenerDatos(url);
        JsonNode root = objectMapper.readTree(response).path("results");

        List<Book> books = new ArrayList<>();
        for (JsonNode node : root) {
            Book book = new Book();
            book.setTitle(node.path("title").asText());
            book.setLanguage(node.path("languages").get(0).asText());
            book.setDownloadCount(node.path("download_count").asInt());

            Author author = new Author();
            JsonNode authorNode = node.path("authors").get(0);
            author.setName(authorNode.path("name").asText());
            author.setBirthYear(authorNode.path("birth_year").asInt());
            author.setDeathYear(authorNode.path("death_year").asInt());

            book.setAuthor(author);
            books.add(book);
        }

        return books;
    }
}

