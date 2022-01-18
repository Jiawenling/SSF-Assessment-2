package com.example.Library.controller;

import com.example.Library.LibraryApplication;
import com.example.Library.model.Books;
import com.example.Library.service.BookCacheService;
import com.example.Library.service.BookService;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.io.IOException;
import java.util.Optional;

import static com.example.Library.model.Books.jsonToBook;

@Controller
public class BookController {

    private Logger logger = LoggerFactory.getLogger(LibraryApplication.class);

    @Autowired
    BookService bookService;



    @Autowired
    BookCacheService bookCacheService;

    @GetMapping("/book/{id}")
    public String viewBook(@PathVariable String id, Model model ) throws IOException {

        Books bookresult;
        Optional<Books> results= bookCacheService.getBooks(id);
        if (results.isPresent()){
            bookresult = results.get();
            bookresult.setCached(true);
            logger.info("results from cache");

        } else{

            JsonObject bookobject = bookService.searchBook(id);
            bookresult = jsonToBook(bookobject);
            logger.info("results from API");
            bookCacheService.saveBooks(id,bookobject.toString());
            logger.info("results saved to cache");
        }
        model.addAttribute("book",bookresult );

        return "bookinfo";
    }


}
