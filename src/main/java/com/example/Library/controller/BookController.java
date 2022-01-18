package com.example.Library.controller;

import com.example.Library.model.Books;
import com.example.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;


    @GetMapping("/results")
    public String showResults(@RequestParam(required = true) String searchTerm, Model model) throws IOException {
        List<String> searchResults = bookService.search(searchTerm);
        model.addAttribute("searchResults",searchResults);
        return "results";
    }

}
