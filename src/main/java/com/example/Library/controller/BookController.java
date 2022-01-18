package com.example.Library.controller;

import com.example.Library.model.Books;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @PostMapping("/")
    public String showResults(Model model){
        model.addAttribute("books",new Books());
        return "index";
    }

}
