package com.example.Library.service;

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


    private Logger logger = LoggerFactory.getLogger(BookService.class);

    public List<String > search(String searchTerm) throws IOException {
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

            JsonArrayBuilder ob = Json.createArrayBuilder();

            fullsearchResults.stream()
                    .map(v-> (JsonObject)v)
                    .forEach(v->{ ob.add(Json.createObjectBuilder()
                                .add("title",v.getJsonString("title"))
                                .add("key", v.getJsonString("key")));
                    });

            return buildUrl(ob.build());

        }
    }

    public List<String> buildUrl(JsonArray jsonArray){
        List<String> urlList = new ArrayList<>();
        String searchURL = "https://openlibrary.org";
            jsonArray.stream()
                    .map(v->(JsonObject)v)
                    .forEach(v -> {
                        urlList.add(searchURL+v.getString("key"));
                    });
            return urlList;
    }
}
