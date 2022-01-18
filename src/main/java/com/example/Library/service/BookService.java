package com.example.Library.service;

import com.example.Library.LibraryApplication;
import com.example.Library.model.Books;
import jakarta.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {


    private Logger logger = LoggerFactory.getLogger(LibraryApplication.class);

    public List<Books> search(String searchTerm) throws IOException {
        searchTerm = searchTerm.trim().replaceAll("\\s","+");
        logger.info("searchterm is: "+ searchTerm);
        RestTemplate template = new RestTemplate();
        String url = UriComponentsBuilder
                .fromUriString("http://openlibrary.org/search.json")
                .queryParam("q", searchTerm)
                .queryParam("limit", 20)
                .toUriString();
        logger.info("url is: "+ url);
        RequestEntity<Void> req = RequestEntity.get(url).build();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();
            JsonArray fullsearchResults = data.getJsonArray("docs");
            JsonObject bookKey = (JsonObject) fullsearchResults.get(0);
            logger.info("key from results= " + bookKey.getJsonString("key"));

            JsonArrayBuilder ob = Json.createArrayBuilder();

            fullsearchResults.stream()
                    .map(v-> (JsonObject)v)
                    .forEach(v->{ ob.add(Json.createObjectBuilder()
                                .add("title",v.getJsonString("title"))
                                .add("key", v.getJsonString("key")));
                    });

            return buildBooks(ob.build());

        }
    }

    public List<Books> buildBooks(JsonArray jsonArray){
        List<Books> bookList = new ArrayList<>();
        String searchURL = "https://openlibrary.org";
            jsonArray.stream()
                    .map(v->(JsonObject)v)
                    .forEach(v -> {
                        Books book = new Books();
                        book.setTitle(v.getString("title"));
                        book.setUrl(searchURL+v.getJsonString("key").getString());
                        book.setId(v.getJsonString("key").getString().replace("/works/",""));
                        bookList.add(book);
                    });

            logger.info("First item in list: " + bookList.get(0).getTitle());
        logger.info("First item in url: " + bookList.get(0).getUrl());
        logger.info("First item in key: " + bookList.get(0).getId());
            return bookList;
    }


}
