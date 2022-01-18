package com.example.Library.service;

import com.example.Library.model.Books;
import com.example.Library.repo.BookRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

@Service
public class BookCacheService {

    @Autowired
    BookRepository bookRepository;

    public void saveBooks(String id, String jsonObject){
        bookRepository.save(id,jsonObject);
    }

    public Optional<Books> getBooks(String id){
        Optional<String> results = bookRepository.getBook(id);
        if(results.isEmpty()){
            return Optional.empty();
        }

        try (InputStream is = new ByteArrayInputStream(results.get().getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject bookJson = reader.readObject();
            return Optional.of(Books.jsonToBook(bookJson));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
