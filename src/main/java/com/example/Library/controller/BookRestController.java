package com.example.Library.controller;

import com.example.Library.service.BookService;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class BookRestController {

    private final Logger logger = LoggerFactory.getLogger(BookRestController.class);

    @Autowired
    BookService bookService;

//    @GetMapping("/")
//    public ResponseEntity<String> showWeather(@RequestParam String searchTerm) {
//
//
//        try{
//
//            Optional<JsonArray> opt = Optional.of(bookService.search(searchTerm));
//            if(opt.isEmpty()){
//                logger.info("City information retrieved from cache");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No results found");
//            } else{
//                return ResponseEntity.ok(opt.get().toString());
//
//            }
//
//
//        } catch(IOException e){
//
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No results found");
//        }
//
//
//    }
}
