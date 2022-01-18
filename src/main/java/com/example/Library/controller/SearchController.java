package com.example.Library.controller;

import com.example.Library.LibraryApplication;
import com.example.Library.model.Books;
import com.example.Library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class SearchController {

    private Logger logger = LoggerFactory.getLogger(LibraryApplication.class);

    @Autowired
    BookService bookService;


    @GetMapping("/results")
    public String showResults(@RequestParam(required = true) String searchTerm, Model model) throws IOException {
        List<Books> searchResults = bookService.search(searchTerm);
        logger.info("First item in controller: " + searchResults.get(0).getTitle());
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("searchResults",searchResults);
        return "results";
    }

}
lts);
        return "results";
    }

}
